# Common
    - Add all tadminuser columns
    - Add all tadminop columns
    - Add findByFullName to the user search endpoint
    - Add findByType to the perm search endpoint
    - Add PIT
    - eTags and caching?
    - OPTIONS verb for actuator paths?
    - Password should not returned in the response body
    - Perhaps the username should be added as a REST path
    - Figure out if there's anything from spring-skeletons we need to bring over
    - create production vs dev app properties
    - Documentation
        - https://www.codingame.com/playgrounds/6740/testing-a-hateoas-service
    - Unit tests
    - Can we actually merge the mysql/postgre/informix implementations and use profiles?
    - User clone functionality?
    - How do the password and password salt fields get populated?
    - How does the timezoneid field get populated?

# QA side testing
    - Postman
        v Fix tests
        v Move the tests from the implementation directories to the root directory
        v Turn the findByLastName request to findByFullName
        v Which port? How do you tell between different implementations?
        - Fix findByFullName, findByUsername and findByType when they're available
            - What about the users,permissions/search resource?
        - Test API for proper validation of bad input
            - Null on username, password, perm name
    - Add cucumber tests
    - Add FitNesse tests

# data-jpa
    - Add DB migration via Flyway
    - Add PATCH verb handling
    - DELETE user/perm should return a 204 No Content
    - Handle non existent user on GET/DELETE/PATCH (404, no body)
    - Handle non existent user on PUT (create new permission)
    - HomeController should return links to users and permissions
    - How do you associate user with permission?
    - Return Hypermedia
    - Returned user's permission must be an empty list instead of null
    - Set data source to MySQL
    - Setup MySQL
    - Should you be able to associate permission with user?

# data-rest
    v Add DB migration via Liquibase
    v Display user id in the response body
    v Display action name in the response body
    v Setup Informix
    v Set data source to Informix
    - Exception handling for null values on username/password via PUT/POST/PATCH
        - Decide which validation strategy to follow
            - https://www.toptal.com/java/spring-boot-rest-api-error-handling
            - https://www.baeldung.com/exception-handling-for-rest-with-spring
            - https://stackoverflow.com/questions/39714193/spring-data-jpa-and-put-requests-to-create
    v what's the equivalent for CURRENT for Timestamp date fields?
        v lastPasswordChange needs a default value of CURRENT
        v what is @Temporal?
        v https://vladmihalcea.com/how-to-store-date-time-and-timestamps-in-utc-time-zone-with-jdbc-and-hibernate/
        v https://vladmihalcea.com/date-timestamp-jpa-hibernate/
    v decide which fields should be insertable, updatable and viewable from the REST API
    - what field is "Third party" on the admin create new user screen?
    - what field is "Automated" on the admin create new user screen?
    - what field is "Prefered Timezone" on the admin create new user screen?
    - clone functionality?
    - what does the "Map account to Betfair Account" section do in the view user screen?
    - handle unique constraints and the exception generated (potentially add Postman scenario as well?)
    v figure out how to validate enum constraints (tadminuser.status|acc_pwd_expires|last_login_status)
    v password_salt should not have a public setter, it should only be set from the password setter
    - password needs to be validated according to PCI rules (grep 'password complies with pci rules' in admin source

# data-redis
    - https://redislabs.com/blog/goodbye-cache-redis-as-a-primary-database/
