databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1505494408043-1") {
		createTable(tableName: "comprobante_fiscal") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "comprobante_fPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "acuse_codigo", type: "varchar(255)")

			column(name: "acuse_estado", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "emisor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "emisor_rfc", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "receptor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "receptor_rfc", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "ultima_validacion", type: "datetime")

			column(name: "uuid", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
