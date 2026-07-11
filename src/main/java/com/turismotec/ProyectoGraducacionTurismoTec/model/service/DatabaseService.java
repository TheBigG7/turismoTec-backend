package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para obtener todos los datos de las tablas de la base de datos
    public String getDatabaseContent() {
        StringBuilder databaseContent = new StringBuilder();

        // 1. Obtener los nombres de todas las tablas
        List<String> tables = jdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = DATABASE()", String.class);

        // 2. Recorrer todas las tablas y extraer los datos
        for (String tableName : tables) {
            databaseContent.append("Tabla: ").append(tableName).append("\n");

            // Obtener las filas de la tabla
            List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM " + tableName);

            for (Map<String, Object> row : rows) {
                databaseContent.append("Fila: ").append(row.toString()).append("\n");
            }
            databaseContent.append("\n");
        }

        return databaseContent.toString();
    }
}
