databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1438952465627-1") {
		addColumn(tableName: "cheque") {
			column(name: "creado_por", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438952465627-2") {
		addColumn(tableName: "cheque") {
			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438952465627-3") {
		addColumn(tableName: "cheque") {
			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438952465627-4") {
		addColumn(tableName: "cheque") {
			column(name: "modificado_por", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	
}
