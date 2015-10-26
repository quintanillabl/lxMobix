databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1445879804442-1") {
		createTable(tableName: "traspaso") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "traspasoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "comision", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_destino_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_origen_id", type: "bigint") {
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

			column(name: "importe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "class", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "importe_isr", type: "decimal(19,2)")

			column(name: "plazo", type: "integer")

			column(name: "rendimiento_calculado", type: "decimal(19,2)")

			column(name: "rendimiento_fecha", type: "datetime")

			column(name: "rendimiento_impuesto", type: "decimal(19,2)")

			column(name: "rendimiento_real", type: "decimal(19,2)")

			column(name: "tasa", type: "decimal(19,2)")

			column(name: "tasa_isr", type: "decimal(19,2)")

			column(name: "vencimiento", type: "datetime")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445879804442-2") {
		addColumn(tableName: "movimiento_de_cuenta") {
			column(name: "traspaso_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445879804442-7") {
		createIndex(indexName: "FK_2r6ntggefm0j6029ux1v87cuy", tableName: "movimiento_de_cuenta") {
			column(name: "traspaso_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445879804442-8") {
		createIndex(indexName: "FK_7887w4jxwwtx7h1ppe79p5dk1", tableName: "traspaso") {
			column(name: "cuenta_origen_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445879804442-9") {
		createIndex(indexName: "FK_7k0v7pr8o24h79su9v53h8gtm", tableName: "traspaso") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445879804442-10") {
		createIndex(indexName: "FK_h333r6bwusfapj3ceoatf535r", tableName: "traspaso") {
			column(name: "cuenta_destino_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445879804442-3") {
		addForeignKeyConstraint(baseColumnNames: "traspaso_id", baseTableName: "movimiento_de_cuenta", constraintName: "FK_2r6ntggefm0j6029ux1v87cuy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "traspaso", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445879804442-4") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_destino_id", baseTableName: "traspaso", constraintName: "FK_h333r6bwusfapj3ceoatf535r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_bancaria", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445879804442-5") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_origen_id", baseTableName: "traspaso", constraintName: "FK_7887w4jxwwtx7h1ppe79p5dk1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_bancaria", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445879804442-6") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "traspaso", constraintName: "FK_7k0v7pr8o24h79su9v53h8gtm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}
}
