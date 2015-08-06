databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1438869705494-1") {
		addColumn(tableName: "cheque") {
			column(name: "cancelacion", type: "datetime")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438869705494-2") {
		addColumn(tableName: "cheque") {
			column(name: "comentario_cancelacion", type: "varchar(255)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438869705494-3") {
		addColumn(tableName: "cheque") {
			column(name: "cuenta_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438869705494-4") {
		addColumn(tableName: "cheque") {
			column(name: "egreso_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438869705494-5") {
		addColumn(tableName: "cheque") {
			column(name: "folio", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438869705494-6") {
		addColumn(tableName: "cheque") {
			column(name: "impresion", type: "datetime")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438869705494-9") {
		createIndex(indexName: "FK_8vx7quji5jdjprt8itihpo14y", tableName: "cheque") {
			column(name: "cuenta_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438869705494-10") {
		createIndex(indexName: "FK_bq2mgi5m5jvvmqw11kra7252h", tableName: "cheque") {
			column(name: "egreso_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438869705494-7") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "cheque", constraintName: "FK_8vx7quji5jdjprt8itihpo14y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_bancaria", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1438869705494-8") {
		addForeignKeyConstraint(baseColumnNames: "egreso_id", baseTableName: "cheque", constraintName: "FK_bq2mgi5m5jvvmqw11kra7252h", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "movimiento_de_cuenta", referencesUniqueColumn: "false")
	}
}
