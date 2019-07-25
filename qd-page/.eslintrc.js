module.exports = {
  root: true,
  'extends': [
    'plugin:vue/essential',
    '@vue/standard'
  ],
  rules: {
    // allow async-await
    'generator-star-spacing': 'off',
    // allow debugger during development
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'vue/no-parsing-error': [2, {
      'x-invalid-end-tag': false
    }],
    'no-undef': 'off',
    'camelcase': 'off',
    // 关闭未使用的变量校验
    'no-unused-vars': 'off',
    // 关闭对象花括号校验
    'object-curly-spacing': 'off',
    'vue/valid-v-else': 'off'
  },
  parserOptions: {
    parser: 'babel-eslint'
  }
}
