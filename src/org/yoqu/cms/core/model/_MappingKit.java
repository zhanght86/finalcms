package org.yoqu.cms.core.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("client", "id", Client.class);
		arp.addMapping("comment", "id", Comment.class);
		arp.addMapping("dictionary", "id", Dictionary.class);
		arp.addMapping("dictionary_type", "id", DictionaryType.class);
		arp.addMapping("file", "id", File.class);
		arp.addMapping("menu", "id", Menu.class);
		arp.addMapping("menu_type", "id", MenuType.class);
		arp.addMapping("module", "id", Module.class);
		arp.addMapping("node", "id", Node.class);
		arp.addMapping("role", "id", Role.class);
		arp.addMapping("role_access", "id", RoleAccess.class);
		arp.addMapping("role_permission", "id", RolePermission.class);
		arp.addMapping("url", "id", Url.class);
		arp.addMapping("user", "id", User.class);
	}
}

