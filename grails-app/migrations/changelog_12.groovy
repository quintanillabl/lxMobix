databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1436911204882-1") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "acuse", type: "mediumblob")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436911204882-2") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "acuse_codigo_estatus", type: "varchar(100)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436911204882-3") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "acuse_estado", type: "varchar(100)")
		}
	}
}
