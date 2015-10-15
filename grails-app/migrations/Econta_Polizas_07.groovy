databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1444832414583-1") {
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

	changeSet(author: "rcancino (generated)", id: "1444832414583-2") {
		createIndex(indexName: "nombre_uniq_1444832413881", tableName: "procesador_de_poliza", unique: "true") {
			column(name: "nombre")
		}
	}
}
