databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1435936425783-1") {
		createTable(tableName: "cierre_contable") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cierre_contabPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435936425783-2") {
		createIndex(indexName: "FK_a7pkkcq9vbm4pw91wluev5r2m", tableName: "banco") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435936425783-3") {
		createIndex(indexName: "cfdi_id_uniq_1435936425184", tableName: "cancelacion_de_cfdi", unique: "true") {
			column(name: "cfdi_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435936425783-4") {
		createIndex(indexName: "FK_dur3ekpmkikkog94i138rm7pw", tableName: "cuenta_contable") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435936425783-5") {
		createIndex(indexName: "FK_p68w656dp8qii1o1irbm4ysar", tableName: "inmueble") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435936425783-6") {
		createIndex(indexName: "FK_g09tk5kfq683jaa36g90w77xa", tableName: "linea") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435936425783-7") {
		createIndex(indexName: "FK_6ea3mj1w1qs5bi2omba0nn2ia", tableName: "producto") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435936425783-8") {
		createIndex(indexName: "FK_ce3ynshakjtaoyy2npi09taut", tableName: "proveedor") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435936425783-9") {
		createIndex(indexName: "FK_eiujinjfwec88boktc88i7ybv", tableName: "requisicion") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435936425783-10") {
		createIndex(indexName: "FK_k0spkb0l34y3bpily5k3jmngo", tableName: "saldo_por_cuenta_contable") {
			column(name: "cuenta_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435936425783-11") {
		createIndex(indexName: "FK_91qmacuyat735y6p88fsblnx5", tableName: "usuario_rol") {
			column(name: "usuario_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435936425783-12") {
		dropColumn(columnName: "fecha", tableName: "saldo_por_cuenta_contable")
	}
}
