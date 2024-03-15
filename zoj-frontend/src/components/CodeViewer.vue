<template>
  <div id="code-viewer">
    <!--    ref="codeViewerRef"-->
    <!--    style="min-height: 300px; height: 40vh" >-->
  </div>
</template>

<script setup lang="ts">
import * as monaco from "monaco-editor";
import { onMounted, ref, toRaw, watch, withDefaults, defineProps } from "vue";

const codeViewerRef = ref();
const codeViewer = ref();

/**
 * 定义组件属性类型
 */
interface Props {
  value: string;
  language?: string;
}

/**
 * 给组件指定初始值
 */
const props = withDefaults(defineProps<Props>(), {
  value: () => "",
  language: () => "java",
});
onMounted(() => {
  if (!codeViewerRef.value) {
    return;
  }
  codeViewer.value = monaco.editor.create(codeViewerRef.value, {
    value: props.value,
    language: props.language,
    // folding: true, // 是否折叠
    // foldingHighlight: true, // 折叠等高线
    // foldingStrategy: "indentation", // 折叠方式  auto | indentation
    // showFoldingControls: "always", // 是否一直显示折叠 always | mouseover
    // disableLayerHinting: true, // 等宽优
    // emptySelectionClipboard: false, // 空选择剪切板
    // selectionClipboard: false, // 选择剪切板
    // automaticLayout: true, // 自动布局
    // scrollBeyondLastLine: false, // 滚动完最后一行后再滚动一屏幕
    // colorDecorators: true, // 颜色装饰器
    // accessibilitySupport: "off", // 辅助功能支持  "auto" | "off" | "on"
    // lineNumbers: "on", // 行号 取值： "on" | "off" | "relative" | "interval" | function
    // lineNumbersMinChars: 1, // 行号最小字符   number
    readOnly: true,
    theme: "vs",
  });
});
</script>

<style scoped></style>
