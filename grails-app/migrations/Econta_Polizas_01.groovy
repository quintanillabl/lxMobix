databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1444066332264-1") {
		createTable(tableName: "poliza") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "polizaPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cierre", type: "datetime")

			column(name: "concepto", type: "varchar(300)") {
				constraints(nullable: "false")
			}

			column(name: "creado_por", type: "varchar(50)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "debe", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "ejercicio", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "haber", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "manual", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "mes", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "modificado_por", type: "varchar(50)")

			column(name: "sub_tipo", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "varchar(7)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-2") {
		createTable(tableName: "poliza_det") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "poliza_detPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "asiento", type: "varchar(20)")

			column(name: "concepto", type: "varchar(50)")

			column(name: "cuenta_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "debe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "entidad", type: "varchar(50)")

			column(name: "haber", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "varchar(20)")

			column(name: "poliza_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "referencia", type: "varchar(255)")

			column(name: "partidas_idx", type: "integer")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-3") {
		createTable(tableName: "poliza_folio") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "poliza_folioPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "ejercicio", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "mes", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sub_tipo", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "varchar(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-4") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "a_favor", tableName: "requisicion")
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-5") {
		addNotNullConstraint(columnDataType: "varchar(50)", columnName: "tipo", tableName: "requisicion")
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-10") {
		createIndex(indexName: "FK_dbcxfi8rv7qm6v7jsh7igs8bg", tableName: "poliza") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-11") {
		createIndex(indexName: "unique_folio", tableName: "poliza", unique: "true") {
			column(name: "tipo")

			column(name: "mes")

			column(name: "ejercicio")

			column(name: "empresa_id")

			column(name: "folio")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-12") {
		createIndex(indexName: "FK_a10ph516nn49dh6mbf974nvah", tableName: "poliza_det") {
			column(name: "cuenta_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-13") {
		createIndex(indexName: "FK_nv2a10uxomue8b8y1faahalx2", tableName: "poliza_det") {
			column(name: "poliza_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-14") {
		createIndex(indexName: "FK_6auooyegq7ncurtu9liv2ooe5", tableName: "poliza_folio") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-15") {
		createIndex(indexName: "unique_folio", tableName: "poliza_folio", unique: "true") {
			column(name: "mes")

			column(name: "tipo")

			column(name: "empresa_id")

			column(name: "folio")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-6") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "poliza", constraintName: "FK_dbcxfi8rv7qm6v7jsh7igs8bg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-7") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "poliza_det", constraintName: "FK_a10ph516nn49dh6mbf974nvah", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-8") {
		addForeignKeyConstraint(baseColumnNames: "poliza_id", baseTableName: "poliza_det", constraintName: "FK_nv2a10uxomue8b8y1faahalx2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "poliza", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1444066332264-9") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "poliza_folio", constraintName: "FK_6auooyegq7ncurtu9liv2ooe5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}
}
