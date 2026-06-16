package com.example.gastape.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "gastope.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        // TABLA USUARIOS
        db.execSQL(
            """
            CREATE TABLE Usuarios(
                idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                correo TEXT UNIQUE NOT NULL,
                contrasena TEXT NOT NULL,
                fechaRegistro TEXT NOT NULL,
                estado INTEGER DEFAULT 1
            )
            """.trimIndent()
        )

        // TABLA CATEGORIAS
        db.execSQL(
            """
            CREATE TABLE Categorias(
                idCategoria INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                icono TEXT,
                color TEXT,
                idUsuario INTEGER NOT NULL,
                FOREIGN KEY(idUsuario)
                REFERENCES Usuarios(idUsuario)
            )
            """.trimIndent()
        )

        // TABLA GASTOS
        db.execSQL(
            """
            CREATE TABLE Gastos(
                idGasto INTEGER PRIMARY KEY AUTOINCREMENT,
                descripcion TEXT NOT NULL,
                monto REAL NOT NULL,
                fecha TEXT NOT NULL,
                observacion TEXT,
                idCategoria INTEGER NOT NULL,
                idUsuario INTEGER NOT NULL,
                
                FOREIGN KEY(idCategoria)
                REFERENCES Categorias(idCategoria),

                FOREIGN KEY(idUsuario)
                REFERENCES Usuarios(idUsuario)
            )
            """.trimIndent()
        )

        // TABLA PRESUPUESTOS
        db.execSQL(
            """
            CREATE TABLE Presupuestos(
                idPresupuesto INTEGER PRIMARY KEY AUTOINCREMENT,
                monto REAL NOT NULL,
                periodo TEXT NOT NULL,
                fechaInicio TEXT NOT NULL,
                fechaFin TEXT NOT NULL,
                idUsuario INTEGER NOT NULL,

                FOREIGN KEY(idUsuario)
                REFERENCES Usuarios(idUsuario)
            )
            """.trimIndent()
        )

        // TABLA RECORDATORIOS
        db.execSQL(
            """
            CREATE TABLE Recordatorios(
                idRecordatorio INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT NOT NULL,
                fechaHora TEXT NOT NULL,
                activo INTEGER DEFAULT 1,
                idUsuario INTEGER NOT NULL,

                FOREIGN KEY(idUsuario)
                REFERENCES Usuarios(idUsuario)
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

        db.execSQL("DROP TABLE IF EXISTS Gastos")
        db.execSQL("DROP TABLE IF EXISTS Categorias")
        db.execSQL("DROP TABLE IF EXISTS Presupuestos")
        db.execSQL("DROP TABLE IF EXISTS Recordatorios")
        db.execSQL("DROP TABLE IF EXISTS Usuarios")

        onCreate(db)
    }
}