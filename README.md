<h1 align="center">Transacción</h1>
<p>Una <b>"transacción"</b> en SQL, cuando se utiliza JDBC en Java, se refiere a una secuencia de operaciones SQL que se ejecutan como una unidad atómica e indivisible. Esto significa que todas las operaciones en la transacción se completan con éxito o ninguna se completa en absoluto. JDBC proporciona soporte para transacciones a través de métodos específicos que permiten comenzar, confirmar o revertir una transacción.</p>
<p>Conceptos clave relacionados con las <b>transacciones</b> en SQL utilizando <b>JDBC</b>:</p>

-    <b>Inicio de una transacción</b>: Antes de ejecutar cualquier operación que forme parte de una transacción, se inicia la transacción utilizando el método `setAutoCommit(false)` en el objeto `Connection`. Esto deshabilita la confirmación automática después de cada operación SQL.

-    <b>Ejecución de operaciones SQL</b>: Dentro de la transacción, es posible ejecutar varias operaciones SQL, como inserciones, actualizaciones o eliminaciones en la base de datos, utilizando objetos `Statement` o `PreparedStatement`.

-    <b>Confirmación de la transacción</b>: Si todas las operaciones se han completado correctamente y se desea confirmar los cambios en la base de datos, se puede llamar al método `commit()` en el objeto `Connection`. Esto guarda permanentemente los cambios realizados en la base de datos.

-    <b>Revertir la transacción (rollback)</b>: Si se produce un error o una condición inesperada durante la transacción y se desea revertir todos los cambios realizados hasta ese punto, se puede llamar al método `rollback()` en el objeto Connection. Esto deshace todas las operaciones realizadas desde el inicio de la transacción.

-    <b>Fin de la transacción: Después de confirmar o revertir la transacción, se puede llamar al método `setAutoCommit(true)` en el objeto `Connection` para restaurar el comportamiento de confirmación automática.
