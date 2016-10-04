databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1475594892000-1") {
		addColumn(tableName: "cuenta_bancaria") {
			column(name: "cuenta_retencion", type: "varchar(255)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1475594892000-2") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "gasto_por_comprobar", type: "bit")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1475594892000-3") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "banco_origen_extranjero", tableName: "transaccion_transferencia")
	}

	changeSet(author: "rcancino (generated)", id: "1475594892000-4") {
		createIndex(indexName: "poliza_det_id_uniq_1475594891193", tableName: "transaccion_compra_nal", unique: "true") {
			column(name: "poliza_det_id")
		}
	}
}
