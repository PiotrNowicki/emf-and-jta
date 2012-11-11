EntityManagerFactory and JTA
===========

Simple project that shows how the JTA EntityManager is behaving in scope of JTA transactions 
when it's created using EntityManagerFactory.

It shows few possible way how to use it. E.g. the EntityManagerFactory creates EntityManager:

* in place where there is a surrounding JTA transaction,
* in place where there is no surrounding JTA transaction,
* with manually join to the running JTA transaction,
* that spans across multiple JTA transactions.

All of the examples uses the `UserTransaction` interface, so it uses Bean Managed Transactions (BMT).
It's done just to easier show the described above bahavior - all of the described features are also available in
Container Managed Transactions (CMT). The most important fact is - we're talking about JTA EntityManagers - we're not 
dealing with resource-local EntityManagers.