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

# QA side testing
    - Postman
        - Turn the findByLastName request to findByFullName
        - Move the tests from the implementation directories to the root directory
    - Add cucumber tests
    - Add FitNesse tests

# data-jpa
    - Add DB migration via Liquibase
    - Add PATCH verb handling
    - DELETE user/perm should return a 204 No Content
    - Handle non existent user on GET/DELETE/PATCH (404, no body)
    - Handle non existent user on PUT (create new permission)
    - HomeController should return links to users and permissions
    - How do you associate user with permission?
    - Return Hypermedia
    - Returned user's permission must be an empty list instead of null
    - Setup MySQL
    - Should you be able to associate permission with user?

# data-rest
    - Add DB migration via Flyway
    - Display user id in the response body
    - Display action name in the response body
    - Setup Informix
