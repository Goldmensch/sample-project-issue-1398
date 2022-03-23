package com.example;

import io.micronaut.context.annotation.Type;
import io.micronaut.data.annotation.TypeDef;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.DataType;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.Collection;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RoleRepo extends CrudRepository<Role, Integer> {

    @io.micronaut.data.annotation.Query(value = """
WITH to_delete_role AS (
    SELECT id FROM roles WHERE name = :roleName
),
to_delete_perm AS (
     SELECT id FROM permissions WHERE operation = :operation AND query IN :queries
 )
DELETE FROM role_permissions WHERE role_id IN (SELECT id FROM to_delete_role) AND permission_id IN (SELECT id FROM to_delete_perm);
""")
    void deletePermissions(String roleName, Operation operation, @TypeDef(type = DataType.STRING_ARRAY) String[] queries);
}
