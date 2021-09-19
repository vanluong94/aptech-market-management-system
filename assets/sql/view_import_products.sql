CREATE VIEW `v_import_product` AS
    SELECT 
        import_products.*,
        suppliers.supplier_name,
        employees.employee_name
    FROM
        import_products
            LEFT JOIN
        suppliers ON suppliers.supplier_id = import_products.supplier_id
            LEFT JOIN
        employees ON employees.employee_id = import_products.employee_id;