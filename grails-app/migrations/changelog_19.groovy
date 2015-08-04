databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1438711883909-1") {
		addColumn(tableName: "cobro") {
			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438711883909-3") {
		createIndex(indexName: "FK_gy17puigf17uwltww7ptcta6o", tableName: "cobro") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438711883909-2") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "cobro", constraintName: "FK_gy17puigf17uwltww7ptcta6o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}
}
