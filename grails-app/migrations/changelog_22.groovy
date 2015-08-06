databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1438815755325-1") {
		addColumn(tableName: "cobro") {
			column(name: "ingreso_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438815755325-3") {
		createIndex(indexName: "FK_ehgbl4mhoewy6d3lkg4fcxwix", tableName: "cobro") {
			column(name: "ingreso_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438815755325-2") {
		addForeignKeyConstraint(baseColumnNames: "ingreso_id", baseTableName: "cobro", constraintName: "FK_ehgbl4mhoewy6d3lkg4fcxwix", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "movimiento_de_cuenta", referencesUniqueColumn: "false")
	}
}
