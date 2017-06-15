databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1497478354425-1") {
		addColumn(tableName: "aplicacion_de_cobro") {
			column(name: "cfdi_cobro_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497478354425-2") {
		addColumn(tableName: "aplicacion_de_pago") {
			column(name: "cfdi_xml", type: "mediumblob")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497478354425-3") {
		addColumn(tableName: "aplicacion_de_pago") {
			column(name: "cfdi_xml_file_name", type: "varchar(200)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497478354425-4") {
		addColumn(tableName: "aplicacion_de_pago") {
			column(name: "folio", type: "varchar(20)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497478354425-5") {
		addColumn(tableName: "aplicacion_de_pago") {
			column(name: "serie", type: "varchar(20)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497478354425-6") {
		addColumn(tableName: "aplicacion_de_pago") {
			column(name: "uuid", type: "varchar(40)") {
				constraints(unique: "true")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497478354425-7") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "version_cfdi", type: "varchar(10)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497478354425-9") {
		createIndex(indexName: "FK_lwxjudh5cu6ymqyqi70ihcsqn", tableName: "aplicacion_de_cobro") {
			column(name: "cfdi_cobro_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497478354425-10") {
		createIndex(indexName: "uuid_uniq_1497478353654", tableName: "aplicacion_de_pago", unique: "true") {
			column(name: "uuid")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1497478354425-8") {
		addForeignKeyConstraint(baseColumnNames: "cfdi_cobro_id", baseTableName: "aplicacion_de_cobro", constraintName: "FK_lwxjudh5cu6ymqyqi70ihcsqn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cfdi", referencesUniqueColumn: "false")
	}
}
