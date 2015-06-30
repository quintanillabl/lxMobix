databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1435702180246-1") {
		createTable(tableName: "aplicacion_de_cobro") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "cuenta_por_cobrar_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATE") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "pago_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-2") {
		createTable(tableName: "aplicacion_de_nota") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-3") {
		createTable(tableName: "aplicacion_de_pago") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-4") {
		createTable(tableName: "arrendamiento") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cliente_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(300)")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "dia_de_corte", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "envio_automatico", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "facturacion_automatica", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "fin", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "inicio", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "inmueble_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "precio", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "producto_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "proxima_factura", type: "DATETIME")

			column(name: "renovacion_automatica", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "VARCHAR(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-5") {
		createTable(tableName: "banco") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "banco_sat_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(150)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-6") {
		createTable(tableName: "banco_sat") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_corto", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "razon_social", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-7") {
		createTable(tableName: "cancelacion_de_cfdi") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "aka", type: "MEDIUMBLOB") {
				constraints(nullable: "false")
			}

			column(name: "cfdi_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-8") {
		createTable(tableName: "cfdi") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "acuse", type: "MEDIUMBLOB")

			column(name: "acuse_codigo_estatus", type: "VARCHAR(255)")

			column(name: "acuse_estado", type: "VARCHAR(255)")

			column(name: "cadena_original", type: "LONGTEXT")

			column(name: "creado_por", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "emisor", type: "VARCHAR(600)") {
				constraints(nullable: "false")
			}

			column(name: "emisor_rfc", type: "VARCHAR(13)") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "grupo", type: "VARCHAR(20)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "modificado_por", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "receptor", type: "VARCHAR(600)") {
				constraints(nullable: "false")
			}

			column(name: "receptor_rfc", type: "VARCHAR(13)") {
				constraints(nullable: "false")
			}

			column(name: "referencia", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "serie", type: "VARCHAR(15)") {
				constraints(nullable: "false")
			}

			column(name: "timbrado", type: "DATETIME")

			column(name: "tipo", type: "VARCHAR(8)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "uuid", type: "VARCHAR(300)")

			column(name: "xml", type: "MEDIUMBLOB") {
				constraints(nullable: "false")
			}

			column(name: "xml_name", type: "VARCHAR(200)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-9") {
		createTable(tableName: "cheque") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-10") {
		createTable(tableName: "cliente") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "direccion_calle", type: "VARCHAR(200)")

			column(name: "direccion_codigo_postal", type: "VARCHAR(255)")

			column(name: "direccion_colonia", type: "VARCHAR(255)")

			column(name: "direccion_estado", type: "VARCHAR(255)")

			column(name: "direccion_municipio", type: "VARCHAR(255)")

			column(name: "direccion_numero_exterior", type: "VARCHAR(50)")

			column(name: "direccion_numero_interior", type: "VARCHAR(50)")

			column(name: "direccion_pais", type: "VARCHAR(100)")

			column(name: "email_cfdi", type: "VARCHAR(255)")

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "VARCHAR(13)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-11") {
		createTable(tableName: "cobro") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "anticipo", type: "BIT")

			column(name: "banco_id", type: "BIGINT")

			column(name: "cliente_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(300)")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "forma_de_pago", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "referencia", type: "VARCHAR(20)")

			column(name: "usuario", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-12") {
		createTable(tableName: "cuenta_bancaria") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "activo", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "banco_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_contable_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "dias_inversion_isr", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "folio_final", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "folio_inicial", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "impresion_template", type: "VARCHAR(50)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(100)") {
				constraints(nullable: "false")
			}

			column(name: "numero", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "plazo", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "tasa_de_inversion", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "tasa_isr", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-13") {
		createTable(tableName: "cuenta_contable") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "VARCHAR(100)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_sat_id", type: "BIGINT")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "de_resultado", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(300)") {
				constraints(nullable: "false")
			}

			column(name: "detalle", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "naturaleza", type: "VARCHAR(9)") {
				constraints(nullable: "false")
			}

			column(name: "nivel", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "padre_id", type: "BIGINT")

			column(name: "sub_tipo", type: "VARCHAR(255)")

			column(name: "tipo", type: "VARCHAR(7)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-14") {
		createTable(tableName: "cuenta_por_cobrar") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cfdi_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cliente_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto_tasa", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "saldo", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "tc", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "venta_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-15") {
		createTable(tableName: "cuenta_por_pagar") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cfdi_xml", type: "MEDIUMBLOB")

			column(name: "cfdi_xml_file_name", type: "VARCHAR(200)")

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "creado_por", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_contable_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "descuento", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATE") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "VARCHAR(20)")

			column(name: "importe", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto_tasa", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "modificado_por", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "pagos_aplicados", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "proveedor_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "requisitado", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "retension_isr", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "retension_isr_tasa", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "retension_iva", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "retension_iva_tasa", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "serie", type: "VARCHAR(20)")

			column(name: "sub_total", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_de_cambio", type: "DECIMAL(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "totalmn", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "uuid", type: "VARCHAR(40)")

			column(name: "vencimiento", type: "DATE") {
				constraints(nullable: "false")
			}

			column(name: "class", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-16") {
		createTable(tableName: "cuenta_sat") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "codigo", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "nivel", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "VARCHAR(100)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-17") {
		createTable(tableName: "documento") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "arrendamiento_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "file", type: "MEDIUMBLOB")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-18") {
		createTable(tableName: "empresa") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "certificado_digital", type: "MEDIUMBLOB")

			column(name: "certificado_digital_pfx", type: "MEDIUMBLOB")

			column(name: "clave", type: "VARCHAR(15)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "direccion_calle", type: "VARCHAR(200)")

			column(name: "direccion_codigo_postal", type: "VARCHAR(255)")

			column(name: "direccion_colonia", type: "VARCHAR(255)")

			column(name: "direccion_estado", type: "VARCHAR(255)")

			column(name: "direccion_municipio", type: "VARCHAR(255)")

			column(name: "direccion_numero_exterior", type: "VARCHAR(50)")

			column(name: "direccion_numero_interior", type: "VARCHAR(50)")

			column(name: "direccion_pais", type: "VARCHAR(100)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "llave_privada", type: "MEDIUMBLOB")

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_de_certificado", type: "VARCHAR(20)")

			column(name: "password_pac", type: "VARCHAR(255)")

			column(name: "password_pfx", type: "VARCHAR(255)")

			column(name: "regimen", type: "VARCHAR(300)") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "VARCHAR(13)") {
				constraints(nullable: "false")
			}

			column(name: "timbrado_de_prueba", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "usuario_pac", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-19") {
		createTable(tableName: "folio") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "serie", type: "VARCHAR(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-20") {
		createTable(tableName: "gasto_det") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cantidad", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "concepto", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_contable_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "gasto_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "unidad", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "valor_unitario", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-21") {
		createTable(tableName: "inmueble") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "VARCHAR(40)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_predial", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_calle", type: "VARCHAR(200)")

			column(name: "direccion_codigo_postal", type: "VARCHAR(255)")

			column(name: "direccion_colonia", type: "VARCHAR(255)")

			column(name: "direccion_estado", type: "VARCHAR(255)")

			column(name: "direccion_municipio", type: "VARCHAR(255)")

			column(name: "direccion_numero_exterior", type: "VARCHAR(50)")

			column(name: "direccion_numero_interior", type: "VARCHAR(50)")

			column(name: "direccion_pais", type: "VARCHAR(100)")

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-22") {
		createTable(tableName: "linea") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "VARCHAR(25)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(250)") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-23") {
		createTable(tableName: "movimiento_de_cuenta") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "creado_por", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATE") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "modificado_por", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "referencia", type: "VARCHAR(255)")

			column(name: "class", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "forma_de_pago", type: "VARCHAR(255)")

			column(name: "requisicion_id", type: "BIGINT")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-24") {
		createTable(tableName: "nota_de_credito") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-25") {
		createTable(tableName: "notificacion") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-26") {
		createTable(tableName: "poliza") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cierre", type: "DATETIME")

			column(name: "comentario", type: "VARCHAR(50)")

			column(name: "concepto", type: "VARCHAR(300)") {
				constraints(nullable: "false")
			}

			column(name: "creado_por", type: "VARCHAR(50)")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "debe", type: "DECIMAL(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "ejercicio", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "haber", type: "DECIMAL(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "manual", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "mes", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "modificado_por", type: "VARCHAR(50)")

			column(name: "tipo", type: "VARCHAR(12)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-27") {
		createTable(tableName: "poliza_det") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "asiento", type: "VARCHAR(20)")

			column(name: "concepto", type: "VARCHAR(50)")

			column(name: "cuenta_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "debe", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "entidad", type: "VARCHAR(50)")

			column(name: "haber", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "VARCHAR(20)")

			column(name: "poliza_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "referencia", type: "VARCHAR(255)")

			column(name: "partidas_idx", type: "INT")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-28") {
		createTable(tableName: "poliza_folio") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "ejercicio", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "mes", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "VARCHAR(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-29") {
		createTable(tableName: "producto") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "VARCHAR(40)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "descuento", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "impuesto", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "inventariable", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "linea_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "precio", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "unidad", type: "VARCHAR(11)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-30") {
		createTable(tableName: "proveedor") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "direccion_calle", type: "VARCHAR(200)")

			column(name: "direccion_codigo_postal", type: "VARCHAR(255)")

			column(name: "direccion_colonia", type: "VARCHAR(255)")

			column(name: "direccion_estado", type: "VARCHAR(255)")

			column(name: "direccion_municipio", type: "VARCHAR(255)")

			column(name: "direccion_numero_exterior", type: "VARCHAR(50)")

			column(name: "direccion_numero_interior", type: "VARCHAR(50)")

			column(name: "direccion_pais", type: "VARCHAR(100)")

			column(name: "email", type: "VARCHAR(255)")

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "nacional", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "VARCHAR(13)") {
				constraints(nullable: "false")
			}

			column(name: "www", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-31") {
		createTable(tableName: "renta") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "arrendamiento_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "fecha_de_cobro", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "fin", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "inicio", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "venta_det_id", type: "BIGINT")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-32") {
		createTable(tableName: "requisicion") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "autorizacion", type: "TINYBLOB")

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "pago", type: "DATE") {
				constraints(nullable: "false")
			}

			column(name: "proveedor_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "VARCHAR(6)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-33") {
		createTable(tableName: "requisicion_det") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "cuenta_por_pagar_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "requisicion_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "requisitado", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-34") {
		createTable(tableName: "rol") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-35") {
		createTable(tableName: "saldo_por_cuenta_bancaria") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-36") {
		createTable(tableName: "saldo_por_cuenta_contable") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cierre", type: "DATETIME")

			column(name: "cuenta_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "debe", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "ejercicio", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATE") {
				constraints(nullable: "false")
			}

			column(name: "haber", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "mes", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "saldo_final", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "saldo_inicial", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-37") {
		createTable(tableName: "tipo_de_cambio") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "factor", type: "DECIMAL(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "fuente", type: "VARCHAR(200)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "moneda_fuente", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "moneda_origen", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-38") {
		createTable(tableName: "usuario") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "apellido_materno", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "apellido_paterno", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "VARCHAR(255)")

			column(name: "enabled", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "nombres", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_de_empleado", type: "INT")

			column(name: "password", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "puesto", type: "VARCHAR(30)")

			column(name: "sucursal", type: "VARCHAR(20)")

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-39") {
		createTable(tableName: "usuario_rol") {
			column(name: "usuario_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "rol_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-40") {
		createTable(tableName: "venta") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "abonos", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "cancelada", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "cfdi_id", type: "BIGINT")

			column(name: "cliente_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "creado_por", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "descuento", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "forma_de_pago", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto_tasa", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "modificado_por", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "pagos", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "sub_total", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "VARCHAR(13)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-41") {
		createTable(tableName: "venta_det") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cantidad", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "descuento", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "descuento_tasa", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "importe_bruto", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "importe_neto", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "precio", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "producto_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "venta_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "partidas_idx", type: "INT")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-42") {
		addPrimaryKey(columnNames: "usuario_id, rol_id", tableName: "usuario_rol")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-99") {
		createIndex(indexName: "unique_nombre", tableName: "banco", unique: "true") {
			column(name: "empresa_id")

			column(name: "nombre")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-100") {
		createIndex(indexName: "UK_r8k4payfynb9hhx2jxs4mewcm", tableName: "banco_sat", unique: "true") {
			column(name: "clave")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-101") {
		createIndex(indexName: "UK_7nmch6c0klcv5tjdw37rm20mk", tableName: "cliente", unique: "true") {
			column(name: "nombre")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-102") {
		createIndex(indexName: "unique_clave", tableName: "cuenta_contable", unique: "true") {
			column(name: "empresa_id")

			column(name: "clave")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-103") {
		createIndex(indexName: "UK_o9q94s3irjm3hklbqyk6lcfo8", tableName: "cuenta_sat", unique: "true") {
			column(name: "codigo")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-104") {
		createIndex(indexName: "UK_2fqlxbcs4h827hio1qam0dhd3", tableName: "empresa", unique: "true") {
			column(name: "nombre")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-105") {
		createIndex(indexName: "UK_ihkcoqh5fdjlaag9r159a7ipn", tableName: "empresa", unique: "true") {
			column(name: "clave")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-106") {
		createIndex(indexName: "unique_clave", tableName: "inmueble", unique: "true") {
			column(name: "empresa_id")

			column(name: "clave")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-107") {
		createIndex(indexName: "unique_clave", tableName: "linea", unique: "true") {
			column(name: "empresa_id")

			column(name: "clave")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-108") {
		createIndex(indexName: "unique_folio", tableName: "poliza", unique: "true") {
			column(name: "tipo")

			column(name: "mes")

			column(name: "ejercicio")

			column(name: "empresa_id")

			column(name: "folio")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-109") {
		createIndex(indexName: "unique_folio", tableName: "poliza_folio", unique: "true") {
			column(name: "mes")

			column(name: "tipo")

			column(name: "empresa_id")

			column(name: "folio")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-110") {
		createIndex(indexName: "unique_clave", tableName: "producto", unique: "true") {
			column(name: "empresa_id")

			column(name: "clave")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-111") {
		createIndex(indexName: "unique_nombre", tableName: "proveedor", unique: "true") {
			column(name: "empresa_id")

			column(name: "nombre")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-112") {
		createIndex(indexName: "unique_folio", tableName: "requisicion", unique: "true") {
			column(name: "empresa_id")

			column(name: "folio")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-113") {
		createIndex(indexName: "UK_pbdeb23es4jm3il5dvs9ec1jb", tableName: "rol", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-114") {
		createIndex(indexName: "unique_cuenta_id", tableName: "saldo_por_cuenta_contable", unique: "true") {
			column(name: "cuenta_id")

			column(name: "mes")

			column(name: "ejercicio")

			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-115") {
		createIndex(indexName: "UK_863n1y3x0jalatoir4325ehal", tableName: "usuario", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-43") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_por_cobrar_id", baseTableName: "aplicacion_de_cobro", baseTableSchemaName: "lx_mobix", constraintName: "FK_fru0dv7xpkbhslnwnc7cf5k9m", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cuenta_por_cobrar", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-44") {
		addForeignKeyConstraint(baseColumnNames: "pago_id", baseTableName: "aplicacion_de_cobro", baseTableSchemaName: "lx_mobix", constraintName: "FK_fj40ckhtb7tppyhg3ap5r6imq", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cobro", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-45") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "arrendamiento", baseTableSchemaName: "lx_mobix", constraintName: "FK_r07kdtrmd16rtgdul76rwfe55", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cliente", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-46") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "arrendamiento", baseTableSchemaName: "lx_mobix", constraintName: "FK_65pwjo7pie1a7tia4o1pjhsep", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-47") {
		addForeignKeyConstraint(baseColumnNames: "inmueble_id", baseTableName: "arrendamiento", baseTableSchemaName: "lx_mobix", constraintName: "FK_l6vnaocsgcq2p8aim8y2k39va", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "inmueble", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-48") {
		addForeignKeyConstraint(baseColumnNames: "producto_id", baseTableName: "arrendamiento", baseTableSchemaName: "lx_mobix", constraintName: "FK_dvvsvm8am7s8lbyw07a0rfnon", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "producto", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-49") {
		addForeignKeyConstraint(baseColumnNames: "banco_sat_id", baseTableName: "banco", baseTableSchemaName: "lx_mobix", constraintName: "FK_ojbiy9td4xewidk3tfiq5143v", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "banco_sat", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-50") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "banco", baseTableSchemaName: "lx_mobix", constraintName: "FK_a7pkkcq9vbm4pw91wluev5r2m", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-51") {
		addForeignKeyConstraint(baseColumnNames: "cfdi_id", baseTableName: "cancelacion_de_cfdi", baseTableSchemaName: "lx_mobix", constraintName: "FK_alib7rwpbtqfnrrkov7gg4rab", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cfdi", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-52") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "cliente", baseTableSchemaName: "lx_mobix", constraintName: "FK_qd3ig3hh15s6t3dvfxdfvwusv", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-53") {
		addForeignKeyConstraint(baseColumnNames: "banco_id", baseTableName: "cobro", baseTableSchemaName: "lx_mobix", constraintName: "FK_irq69fmuqoqvecei8hvxbolmb", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "banco", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-54") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "cobro", baseTableSchemaName: "lx_mobix", constraintName: "FK_htse5ou6xw96aok2100arwug6", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cliente", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-55") {
		addForeignKeyConstraint(baseColumnNames: "banco_id", baseTableName: "cuenta_bancaria", baseTableSchemaName: "lx_mobix", constraintName: "FK_5fnis9wtwr4kj6oygdmt2rl6x", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "banco", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-56") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "cuenta_bancaria", baseTableSchemaName: "lx_mobix", constraintName: "FK_epkeredwxd0x4gvwntysvskue", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-57") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "cuenta_bancaria", baseTableSchemaName: "lx_mobix", constraintName: "FK_2wh2wnefe41rdcye7w73dcy2i", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-58") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_sat_id", baseTableName: "cuenta_contable", baseTableSchemaName: "lx_mobix", constraintName: "FK_98j7qng4c7yv8vqqy5ft8edpr", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cuenta_sat", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-59") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "cuenta_contable", baseTableSchemaName: "lx_mobix", constraintName: "FK_dur3ekpmkikkog94i138rm7pw", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-60") {
		addForeignKeyConstraint(baseColumnNames: "padre_id", baseTableName: "cuenta_contable", baseTableSchemaName: "lx_mobix", constraintName: "FK_9i9d102x7j2sxdynppo87g98h", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-61") {
		addForeignKeyConstraint(baseColumnNames: "cfdi_id", baseTableName: "cuenta_por_cobrar", baseTableSchemaName: "lx_mobix", constraintName: "FK_dr23cegmofr6cniutae8itpg4", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cfdi", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-62") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "cuenta_por_cobrar", baseTableSchemaName: "lx_mobix", constraintName: "FK_nrd26qkdpprl1q8nqsff7wsdh", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cliente", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-63") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "cuenta_por_cobrar", baseTableSchemaName: "lx_mobix", constraintName: "FK_hxtfbqw8q8260wysm7xu9untn", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-64") {
		addForeignKeyConstraint(baseColumnNames: "venta_id", baseTableName: "cuenta_por_cobrar", baseTableSchemaName: "lx_mobix", constraintName: "FK_r4p74krqxw6a6o0i2uv60qwuv", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "venta", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-65") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "cuenta_por_pagar", baseTableSchemaName: "lx_mobix", constraintName: "FK_gsif3fmkt5ysn1q0ndk0mugbj", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-66") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "cuenta_por_pagar", baseTableSchemaName: "lx_mobix", constraintName: "FK_hf5ecf42k3discvv6rsgoh7od", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-67") {
		addForeignKeyConstraint(baseColumnNames: "proveedor_id", baseTableName: "cuenta_por_pagar", baseTableSchemaName: "lx_mobix", constraintName: "FK_t2qqjhf1xl8obm68uavren01k", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "proveedor", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-68") {
		addForeignKeyConstraint(baseColumnNames: "arrendamiento_id", baseTableName: "documento", baseTableSchemaName: "lx_mobix", constraintName: "FK_sxh12a3tdyyepxyvu9rb9j3hi", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "arrendamiento", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-69") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "folio", baseTableSchemaName: "lx_mobix", constraintName: "FK_k3picaa26k7p2wdo22fvq5vw0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-70") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "gasto_det", baseTableSchemaName: "lx_mobix", constraintName: "FK_m0xjsk5eb3b2mwml8is8tmtsf", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-71") {
		addForeignKeyConstraint(baseColumnNames: "gasto_id", baseTableName: "gasto_det", baseTableSchemaName: "lx_mobix", constraintName: "FK_3ql7ryrw5fcb00trxb52qq0ww", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cuenta_por_pagar", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-72") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "inmueble", baseTableSchemaName: "lx_mobix", constraintName: "FK_p68w656dp8qii1o1irbm4ysar", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-73") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "linea", baseTableSchemaName: "lx_mobix", constraintName: "FK_g09tk5kfq683jaa36g90w77xa", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-74") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "movimiento_de_cuenta", baseTableSchemaName: "lx_mobix", constraintName: "FK_s8x1ns782l7aifmk1hne6ha4b", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cuenta_bancaria", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-75") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "movimiento_de_cuenta", baseTableSchemaName: "lx_mobix", constraintName: "FK_npg4bfnnkpw625iowhkx4isq8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-76") {
		addForeignKeyConstraint(baseColumnNames: "requisicion_id", baseTableName: "movimiento_de_cuenta", baseTableSchemaName: "lx_mobix", constraintName: "FK_t96ptfe5ra6u130ynnipmpx1q", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "requisicion", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-77") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "poliza", baseTableSchemaName: "lx_mobix", constraintName: "FK_dbcxfi8rv7qm6v7jsh7igs8bg", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-78") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "poliza_det", baseTableSchemaName: "lx_mobix", constraintName: "FK_a10ph516nn49dh6mbf974nvah", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-79") {
		addForeignKeyConstraint(baseColumnNames: "poliza_id", baseTableName: "poliza_det", baseTableSchemaName: "lx_mobix", constraintName: "FK_nv2a10uxomue8b8y1faahalx2", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "poliza", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-80") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "poliza_folio", baseTableSchemaName: "lx_mobix", constraintName: "FK_6auooyegq7ncurtu9liv2ooe5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-81") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "producto", baseTableSchemaName: "lx_mobix", constraintName: "FK_6ea3mj1w1qs5bi2omba0nn2ia", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-82") {
		addForeignKeyConstraint(baseColumnNames: "linea_id", baseTableName: "producto", baseTableSchemaName: "lx_mobix", constraintName: "FK_kb51cvea57o5gjik7wogls2lv", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "linea", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-83") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "proveedor", baseTableSchemaName: "lx_mobix", constraintName: "FK_ce3ynshakjtaoyy2npi09taut", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-84") {
		addForeignKeyConstraint(baseColumnNames: "arrendamiento_id", baseTableName: "renta", baseTableSchemaName: "lx_mobix", constraintName: "FK_eq95wcg4ayradwryebd1o15eh", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "arrendamiento", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-85") {
		addForeignKeyConstraint(baseColumnNames: "venta_det_id", baseTableName: "renta", baseTableSchemaName: "lx_mobix", constraintName: "FK_mdip5i12date6ijnw7jaatqbe", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "venta_det", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-86") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "requisicion", baseTableSchemaName: "lx_mobix", constraintName: "FK_eiujinjfwec88boktc88i7ybv", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-87") {
		addForeignKeyConstraint(baseColumnNames: "proveedor_id", baseTableName: "requisicion", baseTableSchemaName: "lx_mobix", constraintName: "FK_kaewyu919vxku08l77bammjn6", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "proveedor", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-88") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_por_pagar_id", baseTableName: "requisicion_det", baseTableSchemaName: "lx_mobix", constraintName: "FK_it283nwgeg7fnpcvwl447egq5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cuenta_por_pagar", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-89") {
		addForeignKeyConstraint(baseColumnNames: "requisicion_id", baseTableName: "requisicion_det", baseTableSchemaName: "lx_mobix", constraintName: "FK_bq3t6g2jnt9olgd8dp9xvxef1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "requisicion", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-90") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "saldo_por_cuenta_contable", baseTableSchemaName: "lx_mobix", constraintName: "FK_k0spkb0l34y3bpily5k3jmngo", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-91") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "saldo_por_cuenta_contable", baseTableSchemaName: "lx_mobix", constraintName: "FK_q3wvgkfxlqwtvlytx742cmmfn", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-92") {
		addForeignKeyConstraint(baseColumnNames: "rol_id", baseTableName: "usuario_rol", baseTableSchemaName: "lx_mobix", constraintName: "FK_5gtipd65p6pda9ltx23lm68ge", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "rol", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-93") {
		addForeignKeyConstraint(baseColumnNames: "usuario_id", baseTableName: "usuario_rol", baseTableSchemaName: "lx_mobix", constraintName: "FK_91qmacuyat735y6p88fsblnx5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "usuario", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-94") {
		addForeignKeyConstraint(baseColumnNames: "cfdi_id", baseTableName: "venta", baseTableSchemaName: "lx_mobix", constraintName: "FK_djj7v2yd0m3eqhkupws77328r", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cfdi", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-95") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "venta", baseTableSchemaName: "lx_mobix", constraintName: "FK_ga1c0oyebvdlmcw6s46875ura", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cliente", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-96") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "venta", baseTableSchemaName: "lx_mobix", constraintName: "FK_8sa2vqhedrcha2sjg3jmh091m", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "empresa", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-97") {
		addForeignKeyConstraint(baseColumnNames: "producto_id", baseTableName: "venta_det", baseTableSchemaName: "lx_mobix", constraintName: "FK_ky077dqo04bpiu84fjub7h6yo", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "producto", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435702180246-98") {
		addForeignKeyConstraint(baseColumnNames: "venta_id", baseTableName: "venta_det", baseTableSchemaName: "lx_mobix", constraintName: "FK_ct6vsk3aaug4ooeschvi2tymv", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "venta", referencedTableSchemaName: "lx_mobix", referencesUniqueColumn: "false")
	}
}
