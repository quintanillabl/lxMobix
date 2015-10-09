databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1444094698434-1") {
		addColumn(tableName: "arrendamiento") {
			column(name: "cuenta_deudora_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444094698434-2") {
		addColumn(tableName: "cliente") {
			column(name: "cuenta_contable_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444094698434-3") {
		addColumn(tableName: "poliza_det") {
			column(name: "descripcion", type: "varchar(50)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444094698434-6") {
		createIndex(indexName: "FK_4a6y7htgtk4y0w68chwryvrug", tableName: "arrendamiento") {
			column(name: "cuenta_deudora_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444094698434-7") {
		createIndex(indexName: "FK_jq0l22od31tn0rsx3gaip0bpi", tableName: "cliente") {
			column(name: "cuenta_contable_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444094698434-4") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_deudora_id", baseTableName: "arrendamiento", constraintName: "FK_4a6y7htgtk4y0w68chwryvrug", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1444094698434-5") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "cliente", constraintName: "FK_jq0l22od31tn0rsx3gaip0bpi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}
}
