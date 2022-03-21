# m9JWT


rutas:

    /employee
    /login
    /usuaris
    /hello


Usuari creat:

    {
      "username":"Oriol",
      "password":"password",
      "avatar":"http://imatge.com"
    }

Usuari:
    
    @Id
    @GeneratedValue
    private Long id; //identificador autonumèric
    @Column(unique = true)
    private String username; //no es repeteix username
    private String password;
    private String avatar;
    private String rol = "USER"; //per defecte
    
UsuariDTO:

    private String username;
    private String avatar;
    private String rol;
    
    
Employee:
    
    @javax.persistence.Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Id
    @GeneratedValue
    private String nom;
    private String plataforma;
    private double preu;
    private int horesJugades;
