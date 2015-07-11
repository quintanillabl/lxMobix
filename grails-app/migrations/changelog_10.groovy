databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1436570264027-1") {
		createTable(tableName: "empresa_branding") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "empresa_brandPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "logo", type: "mediumblob")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436570264027-3") {
		createIndex(indexName: "FK_ib2bjgwbm42k8h9yb0n0a86ia", tableName: "empresa_branding") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436570264027-4") {
		createIndex(indexName: "empresa_id_uniq_1436570263415", tableName: "empresa_branding", unique: "true") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436570264027-2") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "empresa_branding", constraintName: "FK_ib2bjgwbm42k8h9yb0n0a86ia", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}
}
