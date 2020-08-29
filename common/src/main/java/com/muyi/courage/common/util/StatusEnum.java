package com.zjft.microservice.treasurybrain.common.util;

import lombok.Getter;

public class StatusEnum {


	/**
	 * 外包人员岗位
	 * 0-外包人员
	 * 1-安保人员
	 * 2-驾驶人员
	 */
	@Getter
	public enum OutSourcingPost{


		OUT_SOURCING(0,"外包人员"),
		PROTECTOR(1,"安保人员"),
		DRIVER(2,"驾驶人员");

		private int post;
		private String name;

		private OutSourcingPost(int post,String name){
			this.post = post;
			this.name = name;
		}
	}


	/**
	 * 线路类型
	 * 0-ATM加钞路线
	 * 1-网点加钞路线
	 * 2-上门收款路线
	 * 3-紧急加钞路线
	 */
	@Getter
	public enum LineType{
		/**
		 * 0-ATM加钞路线
		 * 1-网点加钞路线
		 * 2-上门收款路线
		 * 3-紧急加钞路线
		 */
		ADDNOTES_LINE(0,"ATM加钞路线"),
		NET_LINE(1,"网点加钞路线"),
		CALL_CUSTOMER_LINE(2,"上门收款路线"),
		EMERGENT_ADDNOTES_LINE(3,"紧急加钞路线");

		private int type;
		private String description;

		private LineType(int type,String description){
			this.type = type;
			this.description = description;
		}
	}

	/**
	 * 调拨方向
	 * 1-付出
	 * 2-收入
	 * 3-转运收入
	 */
	@Getter
	public enum TransferDirection{

		GIVE(1,"付出"),
		RECEIVE(2,"收入"),
		TRANSFER(3,"转运收入");

		private int type;
		private String direction;

		private TransferDirection(int type,String direction){
			this.type = type;
			this.direction = direction;
		}
	}
	/**
	 * 现金库房调拨方向记录
	 *
	 * 1-调出
	 * 2-调入
	 *
	 */
	@Getter
	public enum StorageSortedTransferDirection {

		OUT(1,"出库"),
		IN(2,"入库");

		private int direction;
		private String description;

		private StorageSortedTransferDirection(int direction, String description) {
			this.direction = direction;
			this.description = description;
		}
	}

	/**
	 * 现金库房物品位置
	 * 1-在库
	 * 2-已调出
	 *
	 */
	@Getter
	public enum StorageSortedEntityLoaction{
		OUT(2,"库外"),
		IN(1,"库内");


		private int locationType;
		private String description;

		private StorageSortedEntityLoaction(int locationType, String description){
			this.locationType = locationType;
			this.description = description;

		}
	}

	/**
	 * 加钞清机时间端
	 * 时间段类型
	 * 0 该组中不包含顺延法选择设备
	 * 包含顺延法选择设备：
	 * 1- 上午
	 * 2 –下午
	 */
	@Getter
	public enum DevClrTimeInterval {
		/**
		 * 0 该组中不包含顺延法选择设备
		 * 包含顺延法选择设备：
		 * 1- 上午
		 * 2 –下午
		 */
		NOPLAN(0, "该组中不包含顺延法选择设备"),
		FORENOON(1, "上午"),
		AFTERNOON(2, "下午");

		private int type;
		private String name;

		private DevClrTimeInterval(int type, String name) {
			this.type = type;
			this.name = name;
		}

	}

	/**
	 * 特殊SKU编号
	 */
	@Getter
	public enum SkuNo{

		DOCUMENTS("16", "单证"),
		HARD_ONE_YUAN("109", "硬一元"),
		HARD_FIVE_CORNER("111", "硬五角"),
		HARD_ONE_CORNER("114", "硬一角");

		private String skuNo;
		private String description;

		private SkuNo(String skuNo,String description){
			this.skuNo = skuNo;
			this.description = description;
		}
	}

	/**
	 * 读写器状态
	 * <p>
	 * 1：未领用；
	 * 2：已申请；
	 * 3：已领用；
	 * 4：已遗失；
	 * 5：已损坏；
	 */
	@Getter
	public enum TagReaderStatus {

		/**
		 * 1：未领用；
		 * 2：已申请；
		 * 3：已领用；
		 * 4：已遗失；
		 * 5：已损坏；
		 */
		STATUS_UNUSED(1, "未领用"),
		STATUS_APPLIED(2, "已申请"),
		STATUS_TAKED(3, "已领用"),
		STATUS_MISSED(4, "已遗失"),
		STATUS_DAMAGED(5, "已损坏");

		private int status;
		private String name;

		private TagReaderStatus(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}
	@Getter
	public enum DispatchStatus {

		/**
		 * 状态
		 * 99-金库已接单
		 * 1-待揽件
		 * 3-已揽件
		 * 5-已完成
		 * 0-已取消
		 */
		STATUS_CANCEL(0, "已取消"),
		STATUS_RETRIEVAL(1, "待揽件"),
		STATUS_RECEIVED(3, "已揽件 "),
		STATUS_COMPLETE(5, "已完成"),
		STATUS_ORDER(99, "金库已接单");

		private int status;
		private String name;

		private DispatchStatus(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}

	@Getter
	public enum DispatchType {

		/**
		 * 配送单类型
		 * 1-出库单
		 * 2-入库单
		 * 3-转运单
		 */
		TYPE_OUT(1, "出库单"),
		TYPE_ENTER(2, "入库单"),
		TYPE_TRANSPORT(3, "转运单");

		private int status;
		private String name;

		private DispatchType(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}


	@Getter
	public enum OperateType {

		/**
		 * 操作类型
		 * 1-库房出库交接
		 * 2-交接台出库交接
		 * 3-收付交接
		 * 4-入库交接
		 * 5-交接退回
		 * 10-转出
		 * 11-转入
		 */
		TYPE_STOREHOUSE(1, "库房出库交接 "),
		TYPE_HANDOVER_DESK(2, "交接台出库交接"),
		TYPE_PAYMENT(3,"收付交接"),
		TYPE_ENTER(4,"入库交接"),
		TYPE_RETURN(5,"交接退回"),
		TYPE_TRANSFER_OUT(10,"转出"),
		TYPE_TRANSFER_IN(11,"转入");

		private int status;
		private String name;

		private OperateType(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}
	@Getter
	public enum OutboundType {

		/**
		 * 出库类型
		 * 1-库房出库
		 * 2-交接台出库
		 */
		TYPE_STOREHOUSE(1, "库房出库"),
		TYPE_HANDOVER_DESK(2, "交接台出库");

		private int status;
		private String name;

		private OutboundType(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}


	@Getter
	public enum CheckoutType {

		/**
		 * 1：库房出库；
		 * 2：交接台出库；
		 **/
		WAREHOUSE(1, "库房出库"),
		DELIVERY(2, "交接台出库");

		private int type;
		private String name;

		private CheckoutType(int type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 独写器领用记录状态表
	 * 1：待审核；
	 * 2：已审核（通过）；
	 * 3：已审核（拒绝）；
	 * 4：已归还；
	 */
	@Getter
	public enum TagReaderUseStatus {
		/**
		 * 1：待审核；
		 * 2：已审核（通过）；
		 * 3：已审核（拒绝）；
		 * 4：已归还
		 */
		STATUS_TOAUDIT(1, "待审核"),
		STATUS_AUDITED(2, "已审核（通过）"),
		STATUS_RSFUSED(3, "已审核（拒绝）"),
		STATUS_RETURNED(4, "已归还");


		private int status;
		private String name;

		private TagReaderUseStatus(int status, String name) {
			this.status = status;
			this.name = name;
		}

	}

	/**
	 * 独写器领用记录状态表
	 * 1：待审核；
	 * 2：已审核（通过）；
	 * 3：已审核（拒绝）；
	 * 4：已归还；
	 */
	@Getter
	public enum DevStatus {
		/**
		 * 0: 未执行
		 * 1：已备钞
		 * 2：已出库
		 * 3：已回收
		 * 4：已清点
		 */
		STATUS_UNEXECUTED(0,"未执行"),
		STATUS_LOADNOTE(1, "已备钞"),
		STATUS_SIGNED(2, "已签收"),
		STATUS_RECOVERY(3, "已回收"),
		STATUS_CHECKED(4, "已清点");


		private int status;
		private String name;

		private DevStatus(int status, String name) {
			this.status = status;
			this.name = name;
		}

	}

	/**
	 * 设备清点状态状态
	 * tauro模块
	 * 0:未清点；
	 * 1:已清点
	 */
	@Getter
	public enum DevClearFlag {

		/*0:未清点；1:已清点*/
		FLAG_UNCLEAR(0, "未清点"),
		FLAG_CLEARED(1, "已清点");

		private int flag;
		private String name;

		private DevClearFlag(int flag, String name) {
			this.flag = flag;
			this.name = name;
		}
	}

	/**
	 * 钞袋状态
	 * tauro模块
	 * －1：未启用；
	 * 0：启用；
	 */
	@Getter
	public enum CassetteBagInfoStatus {


		STATUS_UNUSED(-1, "未启用"),
		STATUS_USED(0, "已启用");

		private int status;
		private String name;

		private CassetteBagInfoStatus(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 标签状态
	 * tauro模块
	 * 0：未启用
	 * 1；启用
	 * 2：停用
	 */
	@Getter
	public enum TagStatus {

		STATUS_UNUSED(0, "未启用"),
		STATUS_USED(1, "已启用"),
		STATUS_STOPPED(2, "已停用");

		private int status;
		private String name;

		private TagStatus(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 验收明细状态
	 * mobile和tauro模块
	 * 0-已验收
	 * 1-已寄库
	 * 2-已关联加钞任务
	 * 3-已过期
	 */
	@Getter
	public enum CheckTaskDetailStatus {

		STATUS_CHECKED(0, "已验收"),
		STATUS_STORAGED(1, "已寄库"),
		STATUS_REF_DISPATCH(2, "已关联加钞任务"),
		STATUS_OUT_TIME(3, "已过期");

		private int status;
		private String name;

		private CheckTaskDetailStatus(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 钞袋类型
	 * mobile和tauro模块
	 * 1-计划内钞箱袋
	 * 2-备用钞箱袋
	 * 3-空钞箱袋
	 */
	@Getter
	public enum CheckTaskDetailBagType {

		TYPE_IN_PLAN(1, "计划内钞箱袋"),
		TYPE_PREPARED(2, "备用钞箱袋"),
		TYPE_EMPTY(3, "空钞箱袋");

		private int type;
		private String name;

		private CheckTaskDetailBagType(int type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 加钞任务单状态
	 * mobile模块
	 * 1-未分配人员
	 * 2-待审批
	 * 3-审批通过
	 * 4-退回调整
	 * 5-已取消；
	 * 6-已过期；
	 * 7-已完成
	 * 8-未执行
	 * 9-执行中
	 * 10-流转完成
	 */
	@Getter
	public enum TaskStatusMobile {

		STATUS_UNALLOCATED_OP(1, "未分配人员"),
		STATUS_AUDITING(2, "待审批"),
		STATUS_AUDITED(3, "审批通过"),
		STATUS_RETURNED(4, "退回调整"),
		STATUS_CANCELLED(5, "已取消"),
		STATUS_DEPRECATED(6, "已过期"),
		STATUS_FINISHED(7, "已完成"),
		STATUS_UNEXECUTED(8, "未执行"),
		STATUS_EXECUTING(9, "执行中"),
		STATUS_EXECUTED(10, "流转完成");

		private int status;
		private String name;

		private TaskStatusMobile(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 任务中心任务单状态
	 * task模块
	 * 1-已创建
	 * 0--已取消或已过期
	 * 12--已配钞
	 * 13-已配钞入库
	 * 14-已配钞出库；
	 * 15-已签收；
	 * 16-已回收
	 * 17-已回收入库
	 * 18-已清点
	 * 19-已完成
	 */
	@Getter
	public enum TaskAddnotesStatus {

		STATUS_CREATED(201, "已创建"),
		STATUS_CANCELLED(0, "已取消或已过期"),
		STATUS_NOTES_PERPARED(501, "已配钞"),
		STATUS_NOTES_IN_STORAGE(13, "已配钞入库"),
		STATUS_OUT_OF_STORAGE(14, "已配钞出库"),
		STATUS_SIGNED(15, "已签收"),
		STATUS_RECYCLED(16, "已回收"),
		STATUS_RECYCLED_AND_IN_STORAGE(17, "已回收入库"),
		STATUS_CHECKED(18, "已清点"),
		STATUS_FINISHED(19, "已完成");

		private int status;
		private String name;

		private TaskAddnotesStatus(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 任务中心任务单状态
	 * task模块
	 * 1-已创建
	 * 0--已取消或已过期
	 * 12--已配钞
	 * 13-已配钞入库
	 * 14-已配钞出库；
	 * 15-已签收；
	 * 16-已回收
	 * 17-已回收入库
	 * 18-已清点
	 * 19-已完成
	 */
	@Getter
	public enum TaskInfoStatus {

		STATUS_CREATED(1, "已创建"),
		STATUS_CANCELLED(0, "已取消或已过期"),
		STATUS_NOTES_PERPARED(12, "已配钞"),
		STATUS_NOTES_IN_STORAGE(13, "已配钞入库"),
		STATUS_OUT_OF_STORAGE(14, "已配钞出库"),
		STATUS_SIGNED(15, "已签收"),
		STATUS_RECYCLED(16, "已回收"),
		STATUS_RECYCLED_AND_IN_STORAGE(17, "已回收入库"),
		STATUS_CHECKED(18, "已清点"),
		STATUS_FINISHED(19, "已完成");

		private int status;
		private String name;

		private TaskInfoStatus(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * mobile模块
	 * 加钞计划详情拓展数据来源类型
	 * 0-系统任务单
	 * 1-验收任务单
	 * 2-补录
	 */
	@Getter
	public enum DispatchDetailExpandDataSrcType {
		DISPATCH(0, "系统任务单"),
		CHECK(1, "验收任务单"),
		OTHER(2, "补录");

		private int type;
		private String name;

		private DispatchDetailExpandDataSrcType(int type, String name) {
			this.type = type;
			this.name = name;
		}
	}


	/**
	 * mobile模块
	 * 执行异常标志位
	 * 0-正常
	 * 1-异常
	 */
	@Getter
	public enum CassetteCirCulDetailExFlag {
		EX_FLAG_NORMAL(0, "正常"),
		EX_FLAG_UNNORMAL(1, "异常");
		private int flag;
		private String name;

		private CassetteCirCulDetailExFlag(int flag, String name) {
			this.flag = flag;
			this.name = name;
		}
	}

	/**
	 * mobile模块
	 * 操作类型
	 * 1：装填；
	 * 20：出库待审核；
	 * 2：出库/交接；
	 * 30：虚拟签收待审核；
	 * 3：虚拟签收；
	 * 4：落地；
	 * 50：虚拟回收待审核；
	 * 5：虚拟回收；
	 * 60：入库待审核；
	 * 6：入库/交接；
	 * 7：纸币回收；
	 */
	@Getter
	public enum CassetteCirculDetailOpType {

		OP_TYPE_LOAD(1, "装填"),
		OP_TYPE_OUT_AUDITING(20, "出库待审核"),
		OP_TYPE_OUT(2, "出库/交接"),
		OP_TYPE_SIGN_AUDITING(30, "虚拟签收待审核"),
		OP_TYPE_SIGN(3, "虚拟签收"),
		OP_TYPE_INSTALL(4, "落地"),
		OP_TYPE_RECOVER_AUDITING(50, "虚拟回收待审核"),
		OP_TYPE_RECOVERED(5, "虚拟回收"),
		OP_TYPE_IN_AUDITING(60, "入库待审核"),
		OP_TYPE_IN(6, "入库/交接"),
		OP_TYPE_UNLOAD(7, "纸币回收");

		private int type;
		private String name;

		private CassetteCirculDetailOpType(int type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * mobile模块
	 * 执行异常标志位
	 * 0-正常
	 * 1-领错异常
	 * 2-流转异常
	 */
	@Getter
	public enum CassetteBagCircualDetailExFlag {
		EX_FLAG_NORMAL(0, "正常"),
		EX_FLAG_RECEIVE_EXP(1, "领错异常"),
		EX_FLAG_CIRCULATION_EXP(2, "流转异常");
		private int flag;
		private String name;

		private CassetteBagCircualDetailExFlag(int flag, String name) {
			this.flag = flag;
			this.name = name;
		}
	}

	/**
	 * mobile模块
	 * 钞袋流转信息数据来源类型
	 * 1-加钞任务单
	 * 2-验收任务单
	 * 3-其他
	 */
	@Getter
	public enum CassetteBagCircualDetailDataSrcType {

		DATA_SRC_TYPE_DISPATCH(1, "加钞任务单"),
		DATA_SRC_TYPE_CHECK(2, "验收任务单"),
		DATA_SRC_TYPE_OTHER(3, "其他");
		private int type;
		private String name;

		private CassetteBagCircualDetailDataSrcType(int type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * mobile模块
	 * 钞袋流转状态
	 * 20：出库待审核
	 * 2：出库
	 * 60：入库待审核；
	 * 6：入库
	 */
	@Getter
	public enum CassetteBagCircualDetailStatus {


		STATUS_OUT_AUDITING(20, "出库待审核"),
		STATUS_OUT(2, "出库"),
		STATUS_IN_AUDITING(60, "入库待审核"),
		STATUS_IN(6, "入库");


		private int status;
		private String name;

		private CassetteBagCircualDetailStatus(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * mobile模块
	 * 钞袋类型
	 * 1-计划内钞箱袋
	 * 2-备用钞箱袋
	 * 3-空钞箱袋
	 * 4-未知
	 */
	@Getter
	public enum CassetteBagCircualDetailBagType {

		BAG_TYPE_IN_PLAN(1, "计划内钞箱袋"),
		BAG_TYPE_PREPARED(2, "备用钞箱袋"),
		BAG_TYPE_EMPTY(3, "空钞箱袋"),
		BAG_TYPE_UNKNOWN(4, "未知");

		private int type;
		private String name;

		private CassetteBagCircualDetailBagType(int type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * tauro模块
	 * 钞箱状态
	 * -1:未启用；0：启用；1：已装填；20：出库待审核；2：已出库/已交接；30：虚拟签收待审核；3：已虚拟签收；4：已落地；
	 * 50：虚拟回收待审核；5：已虚拟回收；60：入库待审核；6：已入库/已交接；7：纸币已回收；8：损坏；9：维修；10：报废；
	 */
	@Getter
	public enum CassetteInfoStatus {

		CASSETTE_STATUS_UNUSED(-1, "未启用"),
		CASSETTE_STATUS_STARTUP(0, "启用"),
		CASSETTE_STATUS_LOADED(1, "已装填"),
		CASSETTE_STATUS_OUT_AUDITING(20, "出库待审核"),
		CASSETTE_STATUS_OUT(2, "已出库/已交接"),
		CASSETTE_STATUS_SIGN_AUDITING(30, "虚拟签收待审核"),
		CASSETTE_STATUS_SIGNED(3, "已虚拟签收"),
		CASSETTE_STATUS_INSTALLED(4, "已落地"),
		CASSETTE_STATUS_RECOVER_AUDITING(50, "虚拟回收待审核"),
		CASSETTE_STATUS_RECOVERED(5, "已虚拟回收"),
		CASSETTE_STATUS_IN_ADITING(60, "入库待审核"),
		CASSETTE_STATUS_IN(6, "已入库/已交接"),
		CASSETTE_STATUS_RELOADED(7, "纸币已回收"),
		CASSETTE_STATUS_BROKEN(8, "损坏"),
		CASSETTE_STATUS_REPAIRING(9, "维修"),
		CASSETTE_STATUS_DROPED(10, "报废"),
		;
		private int status;
		private String name;

		private CassetteInfoStatus(int status, String name) {
			this.name = name;
			this.status = status;
		}
	}

	/**
	 * tauro模块
	 * 笼车操作类型
	 * 1-装车 2-卸货
	 */
	@Getter
	public enum TaskShelfUserTypeTauro {

		TYPE_IN(1, "装车"),
		TYPE_OUT(2, "卸货");

		private int type;
		private String name;

		private TaskShelfUserTypeTauro(int type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 笼车状态
	 * 0-未启用 1-已启用 2-占用
	 */
	@Getter
	public enum ShelfTableStatus {

		STATUS_NOT_ENABLE(0, "禁用"),
		STATUS_ALREADY_ENABLE(1, "空闲"),
		STATUS_USED(2, "占用");

		private int status;
		private String name;

		private ShelfTableStatus(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * tauro模块
	 * 任务物品调拨方向
	 * 1-出库 2-入库
	 */
	@Getter
	public enum TaskEntityTableDirectionTauro {

		DIRECTION_IN(1, "出库"),
		DIRECTION_OUT(2, "入库");

		private int direction;
		private String name;

		private TaskEntityTableDirectionTauro(int direction, String name) {
			this.direction = direction;
			this.name = name;
		}
	}

	/**
	 * tauro模块
	 * 容器类型
	 * 1-现金 2-贵金属 3-重空
	 */
	@Getter
	public enum TaskEntityTableEntityTypeTauro {

		ENTITY_TYPE_CASH(1, "现金"),
		ENTITY_TYPE_NOBLE_METAL(2, "贵金属"),
		ENTITY_TYPE_IMPORTANT_BLANK_VOUCHERS(3, "重空");

		private int type;
		private String name;

		private TaskEntityTableEntityTypeTauro(int type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 是否为最下级容器
	 * 0-不是 1-是
	 */
	@Getter
	public enum ContainerLeafFlag {

		FLAG_FLASE(0, "不是"),
		FLAG_TRUE(1, "是");

		private int flag;
		private String name;

		private ContainerLeafFlag(int flag, String name) {
			this.flag = flag;
			this.name = name;
		}
	}

	/**
	 * tauro模块
	 * 任务明细表状态
	 * 1-未执行 2-已备钞 3-已出库 4-已送达 5-已回收 6-已确认回收物品
	 */
	@Getter
	public enum TaskDetailStatusTauro {

		STATUS_UNEXECUTED(1, "未执行"),
		STATUS_NOTES_PREPARED(2, "已备钞"),
		STATUS_OUT_OF_STORAGE(3, "已出库"),
		STATUS_DELIVERED(4, "已送达"),
		STATUS_RECYCLED(5, "已回收"),
		STATUS_CONFIRM_RECYCLED(6, "已确认回收物品");

		int status;
		String name;

		private TaskDetailStatusTauro(int status, String name) {
			this.status = status;
			this.name = name;
		}

		public int getStatus() {
			return this.status;
		}

		public String getName() {
			return this.name;
		}
	}

	/**
	 * 任务单类型表
	 * tauro模块
	 * 1-加钞任务单
	 * 2-调拨任务单
	 */
	@Getter
	public enum TaskTypeTauro {

		TYPE_ADDNOTES(1, "加钞任务单"),
        TYPE_TRANSFER(2,"调拨任务单");
		private int type;
		private String name;

		private TaskTypeTauro(int type, String name) {
			this.type = type;
			this.name = name;
		}
		public int getType() {
			return this.type;
		}

		public String getName() {
			return this.name;
		}
	}

	/**
	 * 加钞任务单状态
	 * tauro模块
	 * 1-已创建
	 * 0-已取消或已过期
	 * 12-已配钞
	 * 13-已配钞入库
	 * 14-已加钞出库；
	 * 15-已签收；
	 * 16-已回收
	 * 17-已回收入库
	 * 18-已尾箱调出
	 * 19-已清点
	 * 20-已完成
	 */
	@Getter
	public enum TaskStatusTauro {
		STATUS_CANCELLD(200, "已取消"),
		STATUS_CREATED(201, "已创建"),
		STATUS_FINISHED(205, "已完成");
//		STATUS_CANCELLD_DEPRECATED(0, "已取消或已过期"),
//		STATUS_NOTES_PERPARED(12, "已配钞"),
//		STATUS_NOTES_IN_STORAGE(13, "已配钞入库"),
//		STATUS_OUT_OF_STORAGE(14, "已加钞出库"),
//		//STATUS_SIGNED(15, "已签收"),
//		//STATUS_RECYCLED(16, "已回收"),
//		STATUS_RECYCLED_AND_IN_STORAGE(17, "已回收入库"),
//		STATUS_TAILBOX_OUT_STORAGE(18, "已尾箱调出"),
//		STATUS_CHECKED(19, "已清点"),
//		STATUS_FINISHED(20, "已完成");

		private int status;
		private String name;

		private TaskStatusTauro(int status, String name) {
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 任务执行记录表
	 * tauro模块
	 * 操作类型
	 * 1-配钞 2-入库 3-出库 4-签收 5-回收 6-入库交接 7-出库交接 8-库房调出清分区 9-清分区调入库房
	 */
	@Getter
	public enum TaskPerRecorderTypeTauro {

		TYPE_NOTES_PREPARED(1, "配钞"),
		TYPE_IN_STORAGE(2, "入库"),
		TYPE_OUT_STORAGE(3, "出库"),
		TYPE_VIRTUAL_SIGN(4, "签收"),
		TYPE_TAILBOX_RECYCLE(5, "回收"),
		TYPE_IN_STORAGE_AND_HANDOVER(6, "入库交接"),
		TYPE_OUT_OF_STORAGE_AND_HANDOVER(7, "出库交接"),
		TYPE_TAILBOX_CHECK(8, "库房调出清分区"),
		TYPE_TAILBOX_CHECK_FINISH(9, "清分区调入库房");


		private int type;
		private String name;

		private TaskPerRecorderTypeTauro(int type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 容器类型表
	 * 容器类型
	 * 1	加钞钞袋
	 * 2	加钞钞箱
	 * 3	解现箱
	 * 4    寄存箱
	 * 5	钱捆
	 * 6	保管盒
	 * 7	现金实物
	 */
	@Getter
	public enum ContainerType {

		TYPE_CASSETTE_BAG(1, "加钞钞袋"),
		TYPE_CASSETTE(2, "加钞钞箱"),
		UNDERSTANDING_BOX(3,"现金款箱"),
		DEPOSIT_BOX(4,"寄库款箱"),
		TYPE_CASH_TIES(5, "钱捆"),
		TYPE_SAFETY_BOX(6, "保管盒"),
		TYPE_CASH_KIND(7, "现金实物");


		private int type;
		private String name;

		private ContainerType(int type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 任务单类型
	 */


	public enum taskType {
		ADDNOTESTASK(1, "加钞任务单"), POINTTRANSFERTASK(2, "网点调缴任务单"),
		DOORCOLLECTIONTASK(3, "上门收款任务单"), PEOPLEBANKTRANSFERTASK(4, "人行调缴任务单"), CASHTASK(5, "同业现金任务单"),
		OUTDATE(6, "已过期");

		private int id;
		private String name;

		private taskType(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}
	}


	/**
	 * 加钞计划状态
	 */
	public enum AddnotesPlanStatus {
		DEV_NOTCHOOSE(0, "未选择设备"),
		DEV_CHOOSE(1, "已选择设备"),
		AMOUNT_PREDICTED(2, "已预测金额"),
		GROUPED(3, "已分组"),
		NEED_AUDIT(4, "待审批"),
		RETURN_ADJUST(5, "退回调整"),
		OUTDATE(6, "已过期"),
		SUBMITTED(7, "审批通过(已出单)");

		private int id;
		private String name;

		private AddnotesPlanStatus(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}

		/**
		 * 判断当前状态能否分组
		 *
		 * @param status
		 * @return
		 */
		public static boolean canGroup(int status) {
			return status == AMOUNT_PREDICTED.getId() || status == GROUPED.getId();
		}

		/**
		 * 判断当前状态能否排序
		 *
		 * @param status
		 * @return
		 */
		public static boolean canSort(int status) {
			return status >= GROUPED.getId();
		}
	}


	/**
	 * 加钞任务状态
	 */
	public enum TaskStatus {
		UNASSIGNED(1, "未分配人员"), UNAUDITTED(2, "待审批"), AUDITTED(3, "审批通过"), REFUSED(4, "退回调整"), CANCELED(5, "已取消"),
		OUTDATE(6, "已过期");

		private int id;
		private String name;

		private TaskStatus(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}
	}

	/**
	 * 设备各模块信息
	 *
	 * @author yt
	 * @since 2013-05-16
	 */
	public enum ModName {
		IDC("IDC", "读卡器"),
		CHK("CHK", "支票读扫描"),
		PBK("PBK", "存折"),
		PIN("PIN", "密码键盘"),
		SIU("SIU", "传感器"),
		DEP("DEP", "信封存款"),
		CAM("CAM", "照相机"),
		CIM("CIM", "存款"),
		CDM("CDM", "取款"),
		SPR("SPR", "结单打印机"),
		RPR("RPR", "凭条打印机"),
		JPR("JPR", "日志打印机"),
		TTU("TTU", "文本终端");

		private String id;
		private String name;

		private ModName(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}


	/**
	 * 是否开通循环
	 *
	 * @author yt
	 * @since 2013-08-21
	 */
	public enum CycleFlag {
		UNCYCLE(0, "未开通循环"), ISCYCLE(1, "已开通循环");

		private int id;
		private String name;

		private CycleFlag(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * 设备类型
	 */
	public enum DevCatalog {
		ATM(10003, "ATM"), CRS(10001, "CRS");

		private int id;
		private String name;

		private DevCatalog(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * 银行机构等级
	 *
	 * @author yyc
	 */
	public enum OrgGrade {
		HEAD_BANK(1, "总行"), BRANCH_BANK(2, "分行"), SUB_BRANCH_BANK(3, "支行"), NETPOINT(4, "网点");

		private int no;
		private String name;

		private OrgGrade(int no, String name) {
			this.no = no;
			this.name = name;
		}

		public int getNo() {
			return this.no;
		}

		public String getName() {
			return this.name;
		}
	}

	/**
	 * 矩阵-点类型
	 *
	 * @author yyc
	 */
	public enum MatrixPointType {
		CENTER(0), NETPOINT(1);
		private int value;

		private MatrixPointType(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public static boolean checkIn(int value) {
			for (MatrixPointType temp : MatrixPointType.values()) {
				if (temp.getValue() == value) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 矩阵-类型
	 *
	 * @author yyc
	 */
	public enum MatrixType {
		ALL(-1), N_TO_N(0), C_TO_N(1), N_TO_C(2),E_TO_E(3), C_TO_E(4), E_TO_C(5), C_TO_C(6);
		private int value;

		private MatrixType(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public static boolean checkIn(int value) {
			for (MatrixType temp : MatrixType.values()) {
				if (temp.getValue() == value) {
					return true;
				}
			}
			return false;
		}

		public static boolean checkDefinite(int value) {
			return MatrixType.checkIn(value) && value != MatrixType.ALL.getValue();
		}
	}

	/**
	 * 矩阵-策略
	 *
	 * @author yyc
	 */
	public enum MatrixTactic {
		ALL(-1), DISTANCE_SHORTEST(13), TIME_SHORTEST(11), NO_EXPRESS(10);
		private int value;

		private MatrixTactic(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public static boolean checkIn(int value) {
			for (MatrixTactic temp : MatrixTactic.values()) {
				if (temp.getValue() == value) {
					return true;
				}
			}
			return false;
		}

		public static boolean checkDefinite(int value) {
			return MatrixTactic.checkIn(value) && value != MatrixTactic.ALL.getValue();
		}
	}

	/**
	 * 加钞任务明细状态
	 */
	public enum TaskDetailStatus {
		UNAUDITTED(0, "未审批通过"), AUDITTED(1, "已审批通过");

		private int id;
		private String name;

		private TaskDetailStatus(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}
	}


	/**
	 * 任务物品装箱清点表 操作类型
	 */
	public enum OpertateType {
		LOAD(1, "配钞"),
		CHECK(2, "清点");

		private int id;
		private String name;

		private OpertateType(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}
	}

	/**
	 * 任务调入调出表 direction
	 */
	public enum Direction {
		OUT(2, "出（配钞）"),
		IN(1, "入（清点）");

		private int id;
		private String name;

		private Direction(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}
	}

	/**
	 * 图表时间参数类型
	 */
	public enum DateParamType {
		ONEMONTH(1, "近一个月"), THREEMONTH(2, "近三个月"), ONEYEAR(3, "近一年"), THISMONTH(4, "当月"), THISYEAR(5, "当年");

		private int id;
		private String name;

		private DateParamType(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}
	}

	/**
	 * 钞处中心 钞处交接记录类型
	 */
	@Getter
	public enum ClrHandoverRecordType {


		FUND_FACE_HANDOVER(1,"款项面交"),
		FUND_PAY_HANDOVER(2,"款项付出"),
		MAT_FACE_HANDOVER(3,"物品面交"),
		MAT_TURNOVER(4,"物品移交"),
		INVENTORY_HAND(5,"清点封包收入"),
		MAT_COLLECT_ACCEPT(6,"物品汇总收入"),
		MAT_COLLECT_PAY(7,"物品汇总付出"),
		DOOR_SERVICE_HAND(8,"上门服务交接"),
		PLATFORM_HAND(9,"交接台交接"),
		INVENTORY_REVIEW_PAY(10,"清点复点付出");

		private int type;
		private String name;

		private ClrHandoverRecordType(int type,String name){
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 钞处中心 钞处物料状态
	 */
	@Getter
	public enum ClrMatTableStatus {

		NOT_INVENTORY(1,"未清点"),
		INVENTORY_NOT_AFFIRM(2,"已清点未确认"),
		NOT_AFFIRM(3,"未确认"),
		OPENBOX_HANDLE(4,"开箱经办"),
		NORMAL_BOX(0,"正常");

		private int status;
		private String name;

		private ClrMatTableStatus(int status,String name){
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 钞处中心 清点差错处理类型
	 */
	@Getter
	public enum ClrFaultTableType {

		LONG_PATTERN(1,"长款"),
		SHORT_PATTERN(2,"短款"),
		VERIFY_BILLING(3,"核实入账"),
		BACK_PACK(4,"退包");

		private int type;
		private String name;

		private ClrFaultTableType(int type,String name){
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 钞处中心 清点差错处理状态
	 */
	@Getter
	public enum ClrFaultTableStatus{

		ALREADY_CREATE(1,"已创建"),
		ALREADY_HANDLE_OVER(2,"已交接"),
		ALREADY_CONFIRM(3,"已确认"),
		ALREADY_PRINT_ERROR(4,"已打印差错单"),
		ALREADY_PRINT_ENVELOPE(5,"已打印信封"),
		ALREADY_RETURN(6,"已交回");
		private int status;
		private String name;

		private ClrFaultTableStatus(int status,String name){
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 仓储中心 盘库类型
	 */
	@Getter
	public enum StorageCheckBagsLogCheckType{

		CHECK_STORAGE_DAY(1,"日终盘库"),
		CHECK_STORAGE_TEMP(2,"临时盘库");

		private int type;
		private String name;

		private StorageCheckBagsLogCheckType(int type,String name){
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 仓储中心 盘库操作类型
	 */
	@Getter
	public enum StorageCheckBagsLogOperateType{

		HANDLE_OPERATE_TYPE(1,"盘库经办"),
		REVIEW_OPERATE_TYPE(2,"盘库复核"),
		AUDIT_OPERATE_TYPE(3,"盘库审核");

		private int type;
		private String name;

		private StorageCheckBagsLogOperateType(int type,String name){
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 仓储中心 仓储容器上下级关系
	 */
	@Getter
	public enum StorageContainerIsLeaf{

		PARENT_FLAG(0,"父级容器"),
		CHILD_FLAG(1,"最下级容器");

		private int flag;
		private String name;

		private StorageContainerIsLeaf(int flag,String name){
			this.flag = flag;
			this.name = name;
		}
	}

	/**
	 * 仓储中心 仓储金库调拨方向
	 */
	@Getter
	public enum StorageDirection{

		IN(1,"调入（入库）"),
		OUT(2,"调出（出库）");

		private int status;
		private String name;

		private StorageDirection(int status,String name){
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 订单中心 分批状态
	 */
	@Getter
	public enum TaskBatchStatus{

		CREATE(1,"已创建"),
		AUDIT(2,"已审批"),
		HAND_OVER(3,"已交接"),
		FINISH(4,"已结束");

		private int status;
		private String name;

		private TaskBatchStatus(int status,String name){
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 订单中心 清点类型
	 */
	@Getter
	public enum TaskCheckTableCheckType{

		HANDLE(1,"清点经办"),
		REVIEW(4,"清点复核");

		private int type;
		private String name;

		private TaskCheckTableCheckType(int type,String name){
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 订单中心 容器上下级关系
	 */
	@Getter
	public enum TaskContainerLeafFlag{

		PARENT_FLAG(1,"父级容器"),
		CHILD_FLAG(4,"最下级容器");

		private int flag;
		private String name;

		private TaskContainerLeafFlag(int flag,String name){
			this.flag = flag;
			this.name = name;
		}
	}

	/**
	 * 订单中心 容器状态
	 */
	@Getter
	public enum TaskContainerStatus{

		NORMAL(0,"正常容器（默认）"),
		QUOTA_HANDLE_DELETE(1,"配款经办删除容器"),
		QUOTA_HANDLE(2,"配款经办容器"),
		INVENTORY(3,"清点容器");

		private int status;
		private String name;

		private TaskContainerStatus(int status,String name){
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 仓储中心 容器状态
	 */
	@Getter
	public enum StorageContainerStatus{

		NORMAL(0,"正常容器（默认）"),
		HANDLE_STATUS(1,"开箱经办容器");

		private int status;
		private String name;

		private StorageContainerStatus(int status,String name){
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 订单中心 订单紧急标志
	 */
	@Getter
	public enum TaskUrgentFlag{

		NORMAL_TASK(0,"正常任务"),
		URGENCY_TASK(1,"紧急任务");

		private int flag;
		private String name;

		private TaskUrgentFlag(int flag,String name){
			this.flag = flag;
			this.name = name;
		}
	}

	/**
	 * 订单中心 订单SKU类型
	 */
	@Getter
	public enum TaskSKUType{

		TEMPORARY_TASK(0, "临时备份数据"),
		NORMAL_TASK(1,"原始（申请）订单数据"),
		QUOTA_TASK(2,"配款订单数据"),
		INVENTORY_TASK(3,"清点订单数据");

		private int type;
		private String name;

		private TaskSKUType(int type,String name){
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 订单中心 订单业务参数类型
	 */
	@Getter
	public enum TaskArgumentType{

		DROP_DOWN_BOX(1,"下拉框型"),
		NUMBER(2,"手动输入数字型"),
		VARCHAR(3,"手动输入字符串型"),
		DATE(4,"日期型"),
		ORG(5,"选择机构型");

		private int type;
		private String name;

		private TaskArgumentType(int type,String name){
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 订单中心 订单业务参数标志
	 */
	@Getter
	public enum TaskArgumentFlag{

		PHYSICS_PARAM(1,"物理参数(数量级单位)"),
		VIRTUAL_PARAM(2,"虚拟参数(非数量级单位)");

		private int flag;
		private String name;

		private TaskArgumentFlag(int flag,String name){
			this.flag = flag;
			this.name = name;
		}
	}

	/**
	 * 订单中心 订单业务参数当前状态
	 */
	@Getter
	public enum TaskArgumentCurrentStatus{

		TEMPORARY_STATUS(0,"配款经办临时数据"),
		NORMAL_STATUS(1,"未清点"),
		QUOTA_STATUS(2,"已配款"),
		INVENTORY_STATUS(3,"已清点");

		private int type;
		private String name;

		private TaskArgumentCurrentStatus(int type,String name){
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * 综合管理中心 容器状态
	 */
	@Getter
	public enum ContainerStatus{

		NOT_ENABLE(0,"不能使用"),
		ALREADY_ENABLE(1,"可以使用");

		private int status;
		private String name;

		private ContainerStatus(int status,String name){
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 综合管理中心 业务编号状态
	 */
	@Getter
	public enum ManageBusinessNoStatus{

		NOT_ENABLE(0,"未启用"),
		ALREADY_BUSY(1,"被占用"),
		ALREADY_ENABLE(2,"可用"),
		ALREADY_ABANDON(3,"废弃");

		private int status;
		private String name;

		private ManageBusinessNoStatus(int status,String name){
			this.status = status;
			this.name = name;
		}
	}

	/**
	 * 物流中心:外勤管理--排班时间段
	 */
	@Getter
	public enum TimeInterval{
		ALL_DAY_SCHEDULE(3,"全天"), AM_SCHEDULE(1,"上午"), PM_SCHEDULE(2,"下午");

		private int timeInterval;
		private String desc;

		private TimeInterval(int timeInterval,String desc){
			this.timeInterval = timeInterval;
			this.desc = desc;
		}
	}
	/**
	 * 渠道中心：客户类型
	 */
	@Getter
	public enum CustomerType{

		VISIT_SERVICE("10001","上门服务"),

		NET_SERVICE("10002","网点服务"),

		SELF_SERVICE("10003","自助机具"),

		BANK_HOSP_SERVICE("10004","银医通服务"),

		GOLDEN_EXCHANGE("10005","黄金交易所"),

		TREASURY_SERVICE("10006","金库间服务"),

		METAL_MANUFACTURER("10007","贵金属厂商"),

		PEOPLE_BANK("10008","人行"),

		CHINA_BANK("10009","中行"),

		TRUSTEESHIP_BANK("10010","挂靠行"),

		CTRIP("10011","携程");

		private String typeNo;

		private String typeDesc;

		private CustomerType(String typeNo,String typeDesc)
		{
			this.typeNo = typeNo;
			this.typeDesc = typeDesc;
		}
	}

	/**
	 * 线路规划：规划类型
	 */
	@Getter
	public enum LinePlanType
	{
		NORMAL_PLAN(1,"常规线路"),

		DATE_PLAN(2,"指定日期类型规划"),

		OTHER_PLAN(11,"其他规划");

		private int type;

		private String desc;

		private LinePlanType(int type,String desc)
		{
			this.type = type;
			this.desc = desc;
		}
	}

	/**
	 * 线路规划：线路规划状态
	 */
	@Getter
	public enum LinePlanStatus{

		RUNNING(1,"RUNNING"),

		ERROR(2,"ERROR"),

		FINISH(3,"FINISH");

		private int status;

		private String desc;

		private LinePlanStatus(int status,String desc)
		{
			this.status = status ;
			this.desc = desc ;
		}
	}

	@Getter
	public enum LinePlanCustomerType{

		ClrCenter("0","金库"),

		BankNetPoint("1","我行网点"),

		OtherNetPoint("2","同业网点"),

		VISIT_SERVICE("3","上门客户");

		private String customerType;

		private String desc;

		private LinePlanCustomerType(String customerType,String desc)
		{
			this.customerType = customerType;
			this.desc = desc;
		}
	}
}
