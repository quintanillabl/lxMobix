databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1445987000272-1") {
		createTable(tableName: "activo_fijo") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "activo_fijoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "adquisicion", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(255)")

			column(name: "consignatario", type: "varchar(255)")

			column(name: "costo_actualizado", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_contable_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "depreciacion_tasa", type: "decimal(6,4)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "factura", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "gasto_det_id", type: "bigint")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "modelo", type: "varchar(50)")

			column(name: "serie", type: "varchar(50)")

			column(name: "tipo_de_depreciacion", type: "varchar(7)") {
				constraints(nullable: "false")
			}

			column(name: "valor_depreciable", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "valor_original", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445987000272-2") {
		createTable(tableName: "depreciacion") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "depreciacionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "activo_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "depreciacion", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "ejercicio", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "mes", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445987000272-7") {
		createIndex(indexName: "FK_5xgst57rm939j3sf46h9okbin", tableName: "activo_fijo") {
			column(name: "cuenta_contable_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445987000272-8") {
		createIndex(indexName: "FK_8nly1twnp0mysdcix7moxx0wr", tableName: "activo_fijo") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445987000272-9") {
		createIndex(indexName: "FK_pfbnrnotkj2377h2shgu3umi0", tableName: "activo_fijo") {
			column(name: "gasto_det_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445987000272-10") {
		createIndex(indexName: "FK_q2v91skdm7rq4l9go90rc13qd", tableName: "depreciacion") {
			column(name: "activo_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445987000272-3") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "activo_fijo", constraintName: "FK_5xgst57rm939j3sf46h9okbin", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445987000272-4") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "activo_fijo", constraintName: "FK_8nly1twnp0mysdcix7moxx0wr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445987000272-5") {
		addForeignKeyConstraint(baseColumnNames: "gasto_det_id", baseTableName: "activo_fijo", constraintName: "FK_pfbnrnotkj2377h2shgu3umi0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "gasto_det", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445987000272-6") {
		addForeignKeyConstraint(baseColumnNames: "activo_id", baseTableName: "depreciacion", constraintName: "FK_q2v91skdm7rq4l9go90rc13qd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "activo_fijo", referencesUniqueColumn: "false")
	}
}
