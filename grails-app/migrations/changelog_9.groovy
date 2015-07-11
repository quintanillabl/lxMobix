databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1436566893161-1") {
		createTable(tableName: "configuracion") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "configuracionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creado_por", type: "varchar(255)") {
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

			column(name: "modificado_por", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "modulo", type: "varchar(12)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436566893161-2") {
		createTable(tableName: "configuracion_atributos") {
			column(name: "atributos", type: "bigint")

			column(name: "atributos_idx", type: "varchar(255)")

			column(name: "atributos_elt", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436566893161-4") {
		createIndex(indexName: "FK_deon55rewv0wipk0fs5cx30w4", tableName: "configuracion") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436566893161-5") {
		createIndex(indexName: "empresa_id_uniq_1436566892336", tableName: "configuracion", unique: "true") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436566893161-3") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "configuracion", constraintName: "FK_deon55rewv0wipk0fs5cx30w4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}
}
