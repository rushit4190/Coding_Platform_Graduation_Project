Backend for Coding_platform project - (in Java SpringBoot)

**Functional requirements -** 

- Guest sessions are allowed. Upon first access to available endpoints, a session token will be generated and sent back in response header, with validity upto 10 days.
- Instance of guest with id will be persisted in database against the session token. Subsequent request with same session token in header will maintain the credibility of the guest. If invalid token is sent in the header, a new instance of guest will be created.
- Guest can see the list of problems in the repository, along with the description, editorial of the individual one.
- A credible guest can also view the list of submission details of problems with respect to oneself.
- Guest can access collection of problems.
- Guest can submit a solution to a problem in desired programming language and can view detailed submission report.

**Architecture**

![Project Architecture.PNG](Project%20Architecture.PNG)

- API definition can be found in [Coding_Platform_API.yaml](Coding_Platform_API.yaml) file.
- MySQL is used for database.
- SpringBoot framework is used for writing the code.
