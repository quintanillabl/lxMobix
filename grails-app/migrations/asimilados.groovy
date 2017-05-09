databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1487790316573-1") {
		createTable(tableName: "asimilado") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "asimiladoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "banco_sat_id", type: "bigint")

			column(name: "clabe", type: "varchar(18)")

			column(name: "curp", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "forma_de_pago", type: "varchar(13)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_de_cuenta", type: "varchar(16)")

			column(name: "rfc", type: "varchar(13)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-2") {
		createTable(tableName: "nomina_asimilado") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "nomina_asimilPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "asimilado_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cfdi_id", type: "bigint")

			column(name: "concepto", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "forma_de_pago", type: "varchar(13)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "pago", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "percepciones", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-3") {
		createTable(tableName: "nomina_asimilado_det") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "nomina_asimilPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "clave_sat", type: "varchar(5)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "importe_excento", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_gravado", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "nomina_asimilado_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "varchar(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-4") {
		createTable(tableName: "tarifa_isr") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tarifa_isrPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cuota_fija", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "ejercicio", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "limite_inferior", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "limite_superior", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "porcentaje", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "varchar(7)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-11") {
		createIndex(indexName: "FK_75706l5733b4l838e4r6cjo7o", tableName: "asimilado") {
			column(name: "banco_sat_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-12") {
		createIndex(indexName: "FK_qk9hr79pjgh4e5anl959q3v17", tableName: "asimilado") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-13") {
		createIndex(indexName: "FK_3jjp0l5od1h99wjkk242oiiyg", tableName: "nomina_asimilado") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-14") {
		createIndex(indexName: "FK_3l64mrthx5aw7gno7sfeqxri", tableName: "nomina_asimilado") {
			column(name: "asimilado_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-15") {
		createIndex(indexName: "FK_o5cpnpk3xhdj15s9s84iiqyyi", tableName: "nomina_asimilado") {
			column(name: "cfdi_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-16") {
		createIndex(indexName: "FK_3pw94reg81do6o7j1l1aq7a9c", tableName: "nomina_asimilado_det") {
			column(name: "nomina_asimilado_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-5") {
		addForeignKeyConstraint(baseColumnNames: "banco_sat_id", baseTableName: "asimilado", constraintName: "FK_75706l5733b4l838e4r6cjo7o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "banco_sat", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-6") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "asimilado", constraintName: "FK_qk9hr79pjgh4e5anl959q3v17", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-7") {
		addForeignKeyConstraint(baseColumnNames: "asimilado_id", baseTableName: "nomina_asimilado", constraintName: "FK_3l64mrthx5aw7gno7sfeqxri", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "asimilado", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-8") {
		addForeignKeyConstraint(baseColumnNames: "cfdi_id", baseTableName: "nomina_asimilado", constraintName: "FK_o5cpnpk3xhdj15s9s84iiqyyi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cfdi", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-9") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "nomina_asimilado", constraintName: "FK_3jjp0l5od1h99wjkk242oiiyg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1487790316573-10") {
		addForeignKeyConstraint(baseColumnNames: "nomina_asimilado_id", baseTableName: "nomina_asimilado_det", constraintName: "FK_3pw94reg81do6o7j1l1aq7a9c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "nomina_asimilado", referencesUniqueColumn: "false")
	}
}
