databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1497459539097-1") {
		addColumn(tableName: "empresa") {
			column(name: "regimen_clave_sat", type: "varchar(20)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497459539097-2") {
		addColumn(tableName: "producto") {
			column(name: "clave_prod_serv", type: "varchar(30)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497459539097-3") {
		addColumn(tableName: "producto") {
			column(name: "clave_unidad_sat", type: "varchar(30)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497459539097-4") {
		addColumn(tableName: "producto") {
			column(name: "unidad_sat", type: "varchar(30)")
		}
	}
}
