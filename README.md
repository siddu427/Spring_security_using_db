Flow of Spring Security Authentication
--------------------------------------

DelegatingFilterProxy 
 -> FilterChainProxy 
 -> SecurityContextPersistenceFilter (in case of successful authentication of the user, this stores the SecurityContextHolder containing SecurityContext containing Authentication in session for further usage so that user has to provide username password only once per session)
 -> UsernamePasswordAuthenticationFilter (returns back Authentication which consists of UserName/Principal, Authorities/Roles and user is authenticated or not information)
 -> AuthenticationManager  
 -> AuthenticationProvider (DAOAuthenticationProvider if UserDetailsService is used. It takes care of comparing and authenticating the username and password provided in the request. It will encode the password given by the user and then compare that encoded password with the version of encoded password retrieved from the database)  
 -> UserDetailsService (developer provided and this will return the UserDetails)  
 -> DAO (developer provided)  
 -> Database
 
 Authorization
 -------------
 FilterSecurityInterceptor is the one which takes care of authorization.
 
 
 
 
 
 
 