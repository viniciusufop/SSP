Ext.define('Ssp.view.admin.AdminMenu', {
	extend: 'Ext.grid.Panel',
	alias : 'widget.AdminMenu',
	id: 'AdminMenu',
	store: Ext.getStore('admin.AdminMenus'),
	columns:[ {header: "",  dataIndex: "title", flex:100} ]
	
});