/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import java.io.FileInputStream;
import java.security.Principal;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author abedh
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter{
    
    private static final String TOKEN = "Authorization"; 
    private FirebaseOptions options;
    

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> headers = requestContext.getHeaders().get(TOKEN);
        if (headers != null && headers.size() > 0){
            String authToken = headers.get(0);
            
            FileInputStream refreshToken = new FileInputStream("C:/Users/abedh/Downloads/service-account-file.json"); 
            
            if (options == null) {
                options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://securityeksamenprojekt-default-rtdb.europe-west1.firebasedatabase.app")
                    .build();
                try {
                    FirebaseApp.initializeApp(options);
                } catch(Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
            
            try {                    
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(authToken);
                String uid = decodedToken.getUid();
                
                requestContext.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return () -> uid;
                    }

                    @Override
                    public boolean isUserInRole(String role) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public boolean isSecure() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                return;
            
            } catch (Exception e) {
                e.printStackTrace(); 
                System.out.println(e.getMessage());
            }
            
        }
        
        Response uauthorizedStatus = Response
                                        .status(Response.Status.UNAUTHORIZED)
                                        .entity("You have to login to access this data")
                                        .build();
        requestContext.abortWith(uauthorizedStatus);
    }
    
}
