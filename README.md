<h1 align="center">Transacción</h1>
<p>Una <b>"transacción"</b> en SQL, cuando se utiliza JDBC en Java, se refiere a una secuencia de operaciones SQL que se ejecutan como una unidad atómica e indivisible. Esto significa que todas las operaciones en la transacción se completan con éxito o ninguna se completa en absoluto. JDBC proporciona soporte para transacciones a través de métodos específicos que permiten comenzar, confirmar o revertir una transacción.</p>
<p>Conceptos clave relacionados con las <b>transacciones</b> en SQL utilizando <b>JDBC</b>:</p>

-    <b>Inicio de una transacción</b>: Antes de ejecutar cualquier operación que forme parte de una transacción, se inicia la transacción utilizando el método `setAutoCommit(false)` en el objeto `Connection`. Esto deshabilita la confirmación automática después de cada operación SQL.

-    <b>Ejecución de operaciones SQL</b>: Dentro de la transacción, es posible ejecutar varias operaciones SQL, como inserciones, actualizaciones o eliminaciones en la base de datos, utilizando objetos `Statement` o `PreparedStatement`.

-    <b>Confirmación de la transacción</b>: Si todas las operaciones se han completado correctamente y se desea confirmar los cambios en la base de datos, se puede llamar al método `commit()` en el objeto `Connection`. Esto guarda permanentemente los cambios realizados en la base de datos.

-    <b>Revertir la transacción (rollback)</b>: Si se produce un error o una condición inesperada durante la transacción y se desea revertir todos los cambios realizados hasta ese punto, se puede llamar al método `rollback()` en el objeto Connection. Esto deshace todas las operaciones realizadas desde el inicio de la transacción.

-    <b>Fin de la transacción: Después de confirmar o revertir la transacción, se puede llamar al método `setAutoCommit(true)` en el objeto `Connection` para restaurar el comportamiento de confirmación automática.

<h2 align="center">setAutoCommit, commit y rollback</h2>

`setAutoCommit(boolean autoCommit)`
-  Este método se utiliza para controlar si las operaciones `SQL` deben confirmarse automáticamente o no.
-  Si `autoCommit` se establece en `true`, cada operación `SQL` se confirma automáticamente después de ejecutarse con éxito.
-  Si `autoCommit` se establece en `false`, las operaciones `SQL` se agrupan en una transacción y deben confirmarse explícitamente usando `commit()` o revertirse usando `rollback()`.
-  Es importante tener en cuenta que, por defecto, la confirmación automática está activada (`autoCommit` es `true`) cuando se crea una nueva conexión.

`commit()`
-  Este método confirma todas las operaciones realizadas desde el inicio de la transacción hasta el momento actual.
-  Una vez que se llama a `commit()`, todas las operaciones realizadas en la transacción se vuelven permanentes y se reflejan en la base de datos.
-  Si hay algún error durante la confirmación, se lanzará una excepción `SQLException` y la transacción quedará en un estado indefinido.

`rollback()`
-  Este método revierte todas las operaciones realizadas desde el inicio de la transacción hasta el momento actual.
-  Se utiliza para deshacer los cambios y restaurar el estado de la base de datos al punto en que se inició la transacción.
-  Si se llama a `rollback()`, se descartan todos los cambios y se restaura la base de datos a su estado anterior a la transacción.
-  Al igual que con `commit()`, si hay algún error al intentar revertir la transacción, se lanzará una excepción `SQLException`.
