package cn.itcast.ssm.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.po.ActiveUser;
import cn.itcast.ssm.po.SysPermission;
import cn.itcast.ssm.po.SysUser;
import cn.itcast.ssm.service.SysService;

/**
 * 
 * <p>
 * Title: CustomRealm
 * </p>
 * <p>
 * Description:鑷畾涔塺ealm
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 浼犳櫤.鐕曢潚
 * @date 2015-3-23涓嬪崍4:54:47
 * @version 1.0
 */
public class CustomRealm extends AuthorizingRealm {
	
	//娉ㄥ叆service
	@Autowired
	private SysService sysService;

	// 璁剧疆realm鐨勫悕绉�
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}

	// 鐢ㄤ簬璁よ瘉
	//娌℃湁杩炴帴鏁版嵁搴撶殑鏂规硶
	/*@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		// token鏄敤鎴疯緭鍏ョ殑鐢ㄦ埛鍚嶅拰瀵嗙爜 
		// 绗竴姝ヤ粠token涓彇鍑虹敤鎴峰悕
		String userCode = (String) token.getPrincipal();

		// 绗簩姝ワ細鏍规嵁鐢ㄦ埛杈撳叆鐨剈serCode浠庢暟鎹簱鏌ヨ
		// ....
	

		// 濡傛灉鏌ヨ涓嶅埌杩斿洖null
		//鏁版嵁搴撲腑鐢ㄦ埛璐﹀彿鏄痾hangsansan
		if(!userCode.equals("zhangsansan")){//
			return null;
		}
		
		
		// 妯℃嫙浠庢暟鎹簱鏌ヨ鍒板瘑鐮�
		String password = "111111";

		// 濡傛灉鏌ヨ鍒拌繑鍥炶璇佷俊鎭疉uthenticationInfo
		
		//activeUser灏辨槸鐢ㄦ埛韬唤淇℃伅
		ActiveUser activeUser = new ActiveUser();
		
		activeUser.setUserid("zhangsan");
		activeUser.setUsercode("zhangsan");
		activeUser.setUsername("寮犱笁");
		//..
		
		//鏍规嵁鐢ㄦ埛id鍙栧嚭鑿滃崟
		//閫氳繃service鍙栧嚭鑿滃崟 
		List<SysPermission> menus  = null;
		try {
			menus = sysService.findMenuListByUserId("zhangsan");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//灏嗙敤鎴疯彍鍗� 璁剧疆鍒癮ctiveUser
		activeUser.setMenus(menus);
		
		
		//灏哸ctiveUser璁剧疆simpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				activeUser, password, this.getName());

		return simpleAuthenticationInfo;
	}*/
	
	//realm鐨勮璇佹柟娉曪紝浠庢暟鎹簱鏌ヨ鐢ㄦ埛淇℃伅
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		// token鏄敤鎴疯緭鍏ョ殑鐢ㄦ埛鍚嶅拰瀵嗙爜 
		// 绗竴姝ヤ粠token涓彇鍑虹敤鎴峰悕
		String userCode = (String) token.getPrincipal();

		// 绗簩姝ワ細鏍规嵁鐢ㄦ埛杈撳叆鐨剈serCode浠庢暟鎹簱鏌ヨ
		SysUser sysUser = null;
		try {
			sysUser = sysService.findSysUserByUserCode(userCode);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 濡傛灉鏌ヨ涓嶅埌杩斿洖null
		if(sysUser==null){//
			return null;
		}
		// 浠庢暟鎹簱鏌ヨ鍒板瘑鐮�
		String password = sysUser.getPassword();
		
		//鐩�
		String salt = sysUser.getSalt();

		// 濡傛灉鏌ヨ鍒拌繑鍥炶璇佷俊鎭疉uthenticationInfo
		
		//activeUser灏辨槸鐢ㄦ埛韬唤淇℃伅
		ActiveUser activeUser = new ActiveUser();
		
		activeUser.setUserid(sysUser.getId());
		activeUser.setUsercode(sysUser.getUsercode());
		activeUser.setUsername(sysUser.getUsername());
		//..
		
		//鏍规嵁鐢ㄦ埛id鍙栧嚭鑿滃崟
		List<SysPermission> menus  = null;
		try {
			//閫氳繃service鍙栧嚭鑿滃崟 
			menus = sysService.findMenuListByUserId(sysUser.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//灏嗙敤鎴疯彍鍗� 璁剧疆鍒癮ctiveUser
		activeUser.setMenus(menus);

		//灏哸ctiveUser璁剧疆simpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				activeUser, password,ByteSource.Util.bytes(salt), this.getName());

		return simpleAuthenticationInfo;
	}
	
	

	// 鐢ㄤ簬鎺堟潈
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		//浠� principals鑾峰彇涓昏韩浠戒俊鎭�
		//灏唃etPrimaryPrincipal鏂规硶杩斿洖鍊艰浆涓虹湡瀹炶韩浠界被鍨嬶紙鍦ㄤ笂杈圭殑doGetAuthenticationInfo璁よ瘉閫氳繃濉厖鍒癝impleAuthenticationInfo涓韩浠界被鍨嬶級锛�
		ActiveUser activeUser =  (ActiveUser) principals.getPrimaryPrincipal();
		
		//鏍规嵁韬唤淇℃伅鑾峰彇鏉冮檺淇℃伅
		//浠庢暟鎹簱鑾峰彇鍒版潈闄愭暟鎹�
		List<SysPermission> permissionList = null;
		try {
			permissionList = sysService.findPermissionListByUserId(activeUser.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//鍗曠嫭瀹氫竴涓泦鍚堝璞� 
		List<String> permissions = new ArrayList<String>();
		if(permissionList!=null){
			for(SysPermission sysPermission:permissionList){
				//灏嗘暟鎹簱涓殑鏉冮檺鏍囩 绗︽斁鍏ラ泦鍚�
				permissions.add(sysPermission.getPercode());
			}
		}
		
		
	/*	List<String> permissions = new ArrayList<String>();
		permissions.add("user:create");//鐢ㄦ埛鐨勫垱寤�
		permissions.add("item:query");//鍟嗗搧鏌ヨ鏉冮檺
		permissions.add("item:add");//鍟嗗搧娣诲姞鏉冮檺
		permissions.add("item:edit");//鍟嗗搧淇敼鏉冮檺
*/		//....
		
		//鏌ュ埌鏉冮檺鏁版嵁锛岃繑鍥炴巿鏉冧俊鎭�(瑕佸寘鎷� 涓婅竟鐨刾ermissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//灏嗕笂杈规煡璇㈠埌鎺堟潈淇℃伅濉厖鍒皊impleAuthorizationInfo瀵硅薄涓�
		simpleAuthorizationInfo.addStringPermissions(permissions);

		return simpleAuthorizationInfo;
	}
	
	//娓呴櫎缂撳瓨
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}


}
