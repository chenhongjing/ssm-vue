<template>
  <div v-is="'script'" :id="editorId" type="text/plain"></div>
</template>
<script>
import '.../../../public/Ueditor/ueditor.config.js'
import '../../../public/Ueditor/ueditor.all.min.js'
import '../../../public/Ueditor/ueditor.parse.js'
import '../../../public/Ueditor/lang/zh-cn/zh-cn.js'
import '../../../public/Ueditor/themes/default/css/ueditor.css'

export default {
  name: 'UeditorWrap',
  props: {
    /* ID */
    editorId: {
      type: String,
      default: ''
    },
    /* 文本内容 */
    modelValue: {
      type: String,
      default: ''
    },
    /* UEditor配置，配置方案参考官方文档 */
    config: Object
  },
  data() {
    return {
      editor: null
    }
  },
  mounted() {
    /**
     * 初始化UEditor
     */
    this.editor = window.UE.getEditor(this.editorId, this.config)
    this.editor.addListener('ready', () => {
      this.editor.setContent(this.modelValue)
    })
    /**
     * 监听文本内容变化，使得U父组件中绑定的数据与之保持一致
     */
    this.editor.addListener('contentChange', () => {
      this.$emit('update:modelValue', this.editor.getContent())
    })
  },
  methods: {
    getUEContent() {
      return this.editor.getContent()
    },
    setUEContent(value) {
      return this.editor.setContent(value)
    }
  },
  unmounted() {
    this.editor.destroy()
  }
}
</script>
