databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1436373229056-1") {
		addColumn(tableName: "arrendamiento") {
			column(name: "cuenta_de_pago", type: "varchar(6)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436373229056-2") {
		addColumn(tableName: "arrendamiento") {
			column(name: "forma_de_pago", type: "varchar(255)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436373229056-3") {
		addColumn(tableName: "venta") {
			column(name: "cuenta_de_pago", type: "varchar(6)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436373229056-4") {
		createIndex(indexName: "cfdi_id_uniq_1436373228466", tableName: "cancelacion_de_cfdi", unique: "true") {
			column(name: "cfdi_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436373229056-5") {
		createIndex(indexName: "FK_qd3ig3hh15s6t3dvfxdfvwusv", tableName: "cliente") {
			column(name: "empresa_id")
		}
	}
}
