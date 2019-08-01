<template>
  <a-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单号">
              <a-input placeholder="请输入订单号" v-decorator="['orderCode', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单类型">
              <a-input placeholder="请输入订单类型" v-decorator="['ctype', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单日期">
              <a-date-picker
                placeholder="请输入订单日期"
                style="width:100%"
                :showTime="true"
                format="YYYY-MM-DD HH:mm:ss"
                v-decorator="[ 'orderDate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单金额">
              <a-input-number placeholder="请输入订单金额" style="width:100%" v-decorator="[ 'orderMoney', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单备注">
              <a-input placeholder="请输入订单备注" v-decorator="['content', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="客户明细" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="testOrderCustomTable.loading"
            :columns="testOrderCustomTable.columns"
            :dataSource="testOrderCustomTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
        <a-tab-pane tab="产品明细" :key="refKeys[1]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[1]"
            :loading="testOrderTicketTable.loading"
            :columns="testOrderTicketTable.columns"
            :dataSource="testOrderTicketTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
      </a-tabs>

    </a-spin>
  </a-modal>
</template>

<script>

  import moment from 'moment'
  import pick from 'lodash.pick'
  import { FormTypes } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'

  export default {
    name: 'MapConfigMainModal',
    mixins: [JEditableTableMixin],
    data() {
      return {
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
        },
        refKeys: ['testOrderCustom', 'testOrderTicket', ],
        activeKey: 'testOrderCustom',
        // 客户明细
        testOrderCustomTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '客户名',
              key: 'name',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '性别',
              key: 'sex',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '身份证号码',
              key: 'idcard',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '身份证扫描件',
              key: 'idcardPic',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '电话1',
              key: 'telphone',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
          ]
        },
        // 产品明细
        testOrderTicketTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '航班号',
              key: 'ticketCode',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '航班时间',
              key: 'tickectDate',
              type: FormTypes.datetime,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
          ]
        },
        url: {
          add: "/test2/MapConfigMain/add",
          edit: "/test2/MapConfigMain/edit",
          testOrderCustom: {
            list: '/test2/MapConfigMain/queryTestOrderCustomByMainId'
          },
          testOrderTicket: {
            list: '/test2/MapConfigMain/queryTestOrderTicketByMainId'
          },
        }
      }
    },
    methods: {
 
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'orderCode', 'ctype', 'orderDate', 'orderMoney', 'content', ))
          // 时间格式化
          this.form.setFieldsValue({ orderDate: this.model.orderDate ? moment(this.model.orderDate) : null })
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.testOrderCustom.list, params, this.testOrderCustomTable)
          this.requestSubTableData(this.url.testOrderTicket.list, params, this.testOrderTicketTable)
        }
      },
 
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        //时间格式化
        main.orderDate = main.orderDate ? main.orderDate.format('YYYY-MM-DD HH:mm:ss') : null;
        return {
          ...main, // 展开
          testOrderCustomList: allValues.tablesValue[0].values,
          testOrderTicketList: allValues.tablesValue[1].values,
        }
      }
    }
  }
</script>

<style scoped>
</style>