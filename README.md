EntityManagerFactory and JTA
===========

Simple project that shows how the JTA `EntityManager` is behaving in scope of JTA transactions 
when it's created using `EntityManagerFactory`.

It shows few possible way how to use it. E.g. the `EntityManagerFactory` creates `EntityManager`:

* in place where there is a surrounding JTA transaction,
* in place where there is no surrounding JTA transaction,
* with manually join to the running JTA transaction,
* that spans across multiple JTA transactions.

All of the examples uses the `UserTransaction` interface, so it uses Bean Managed Transactions (BMT).
It's done just to easier show the described above bahavior - all of the described features are also available in
Container Managed Transactions (CMT). The most important fact is - **we're talking about JTA `EntityManager`s** - 
we're **not** dealing with the resource-local EntityManagers.

By default the application exposes 2 REST endpoints:

* **http://HOST:PORT/emf-jta/rest/execute** - invokes one of the defined methods and returns the list of persisted 
  objects (so you can see if the method succeeded or not); remember to restart / republish the application after testing new method as the database should be cleared.

* **http://HOST:PORT/emf-jta/rest/cdi** - invokes the CDI-related test (injection of request-scoped EntityManager)