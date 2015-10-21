databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1445283713304-1") {
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

			column(name: "fecha", type: "date") {
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

	changeSet(author: "rcancino (generated)", id: "1445283713304-2") {
		createTable(tableName: "poliza_cheque") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "poliza_chequePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "banco_emisor_extranjero", type: "varchar(150)")

			column(name: "banco_emisor_nacional_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "beneficiario", type: "varchar(300)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_origen", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "date") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "monto", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "numero", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "poliza_det_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "varchar(13)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_de_cambio", type: "decimal(19,5)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-3") {
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

			column(name: "descripcion", type: "varchar(255)")

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

	changeSet(author: "rcancino (generated)", id: "1445283713304-4") {
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

			column(name: "mes", type: "integer") {
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

	changeSet(author: "rcancino (generated)", id: "1445283713304-5") {
		createTable(tableName: "procesador_de_poliza") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "procesador_dePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar(255)")

			column(name: "label", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "service", type: "varchar(100)")

			column(name: "tipo", type: "varchar(7)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-6") {
		addColumn(tableName: "arrendamiento") {
			column(name: "cuenta_contable_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-7") {
		addColumn(tableName: "cliente") {
			column(name: "cuenta_contable_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-8") {
		addColumn(tableName: "movimiento_de_cuenta") {
			column(name: "rfc", type: "varchar(13)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-9") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "a_favor", tableName: "requisicion")
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-10") {
		addNotNullConstraint(columnDataType: "varchar(50)", columnName: "tipo", tableName: "requisicion")
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-19") {
		createIndex(indexName: "FK_6dtcfc08ad75psdsmbo09anp7", tableName: "arrendamiento") {
			column(name: "cuenta_contable_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-20") {
		createIndex(indexName: "FK_jq0l22od31tn0rsx3gaip0bpi", tableName: "cliente") {
			column(name: "cuenta_contable_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-21") {
		createIndex(indexName: "FK_dbcxfi8rv7qm6v7jsh7igs8bg", tableName: "poliza") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-22") {
		createIndex(indexName: "unique_folio", tableName: "poliza", unique: "true") {
			column(name: "sub_tipo")

			column(name: "tipo")

			column(name: "mes")

			column(name: "ejercicio")

			column(name: "empresa_id")

			column(name: "folio")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-23") {
		createIndex(indexName: "FK_9lhkdsk6j7glpwbjt8ippqsjt", tableName: "poliza_cheque") {
			column(name: "banco_emisor_nacional_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-24") {
		createIndex(indexName: "FK_hqghvlpio11w8o6j9hcm8vosu", tableName: "poliza_cheque") {
			column(name: "poliza_det_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-25") {
		createIndex(indexName: "poliza_det_id_uniq_1445283712670", tableName: "poliza_cheque", unique: "true") {
			column(name: "poliza_det_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-26") {
		createIndex(indexName: "FK_a10ph516nn49dh6mbf974nvah", tableName: "poliza_det") {
			column(name: "cuenta_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-27") {
		createIndex(indexName: "FK_nv2a10uxomue8b8y1faahalx2", tableName: "poliza_det") {
			column(name: "poliza_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-28") {
		createIndex(indexName: "FK_6auooyegq7ncurtu9liv2ooe5", tableName: "poliza_folio") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-29") {
		createIndex(indexName: "unique_folio", tableName: "poliza_folio", unique: "true") {
			column(name: "ejercicio")

			column(name: "mes")

			column(name: "sub_tipo")

			column(name: "tipo")

			column(name: "empresa_id")

			column(name: "folio")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-30") {
		createIndex(indexName: "nombre_uniq_1445283712672", tableName: "procesador_de_poliza", unique: "true") {
			column(name: "nombre")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-11") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "arrendamiento", constraintName: "FK_6dtcfc08ad75psdsmbo09anp7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-12") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "cliente", constraintName: "FK_jq0l22od31tn0rsx3gaip0bpi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-13") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "poliza", constraintName: "FK_dbcxfi8rv7qm6v7jsh7igs8bg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-14") {
		addForeignKeyConstraint(baseColumnNames: "banco_emisor_nacional_id", baseTableName: "poliza_cheque", constraintName: "FK_9lhkdsk6j7glpwbjt8ippqsjt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "banco_sat", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-15") {
		addForeignKeyConstraint(baseColumnNames: "poliza_det_id", baseTableName: "poliza_cheque", constraintName: "FK_hqghvlpio11w8o6j9hcm8vosu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "poliza_det", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-16") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "poliza_det", constraintName: "FK_a10ph516nn49dh6mbf974nvah", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-17") {
		addForeignKeyConstraint(baseColumnNames: "poliza_id", baseTableName: "poliza_det", constraintName: "FK_nv2a10uxomue8b8y1faahalx2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "poliza", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445283713304-18") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "poliza_folio", constraintName: "FK_6auooyegq7ncurtu9liv2ooe5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}
}
