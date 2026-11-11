
package com.joaopedro.mini_ecomerce.repository;

import com.joaopedro.mini_ecomerce.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {

        String sql = """
                SELECT id, name, description, price_cents, stock
                FROM products
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price_cents"),
                        rs.getInt("stock")
                )
        );
    }

    public String getDatabasePath() {
        return jdbcTemplate.queryForObject(
                "PRAGMA database_list",
                (rs, rowNum) -> rs.getString("file")
        );
    }

    public int save(
            String name,
            String description,
            Integer priceCents,
            Integer stock
    ) {
        String sql = """
        INSERT INTO products
        (name, description, price_cents, stock)
        VALUES (?, ?, ?, ?)
    """;

        return jdbcTemplate.update(
                sql,
                name,
                description,
                priceCents,
                stock
        );
    }

    public int deleteById(Long id) {
        String sql = """
        DELETE FROM products
        WHERE id = ?
    """;

        return jdbcTemplate.update(sql, id);
    }


}


