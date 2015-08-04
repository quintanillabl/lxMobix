databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1438706350555-1") {
		addColumn(tableName: "cobro") {
			column(name: "creado_por", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438706350555-2") {
		addColumn(tableName: "cobro") {
			column(name: "cuenta_destino_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438706350555-3") {
		addColumn(tableName: "cobro") {
			column(name: "modificado_por", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438706350555-4") {
		modifyDataType(columnName: "anticipo", newDataType: "bit", tableName: "cobro")
	}

	changeSet(author: "rcancino (generated)", id: "1438706350555-5") {
		addNotNullConstraint(columnDataType: "bit", columnName: "anticipo", tableName: "cobro")
	}

	changeSet(author: "rcancino (generated)", id: "1438706350555-6") {
		addNotNullConstraint(columnDataType: "varchar(255)", columnName: "usuario", tableName: "cobro")
	}

	changeSet(author: "rcancino (generated)", id: "1438706350555-8") {
		createIndex(indexName: "FK_cp6u78uaqnm9axnn58usfjhsp", tableName: "cobro") {
			column(name: "cuenta_destino_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438706350555-7") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_destino_id", baseTableName: "cobro", constraintName: "FK_cp6u78uaqnm9axnn58usfjhsp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_bancaria", referencesUniqueColumn: "false")
	}
}
