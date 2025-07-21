import java.sql.Connection

inline fun Connection.withTransaction(block: Connection.() -> Unit) {
    autoCommit = false
    try {
        block()
        commit()
    } catch (e: Exception) {
        try {
            rollback()
        } catch (rollbackException: Exception) {
            e.addSuppressed(rollbackException)
        }
        throw e
    } finally {
        autoCommit = true
    }
}