const { defineConfig } = require('@vue/cli-service');
const path = require('path');
module.exports = defineConfig({
  transpileDependencies: true,
  configureWebpack: {
    plugins: [
      {
        apply: (compiler) => {
          compiler.hooks.done.tap('AddMetaInfPlugin', () => {
            const fs = require('fs');
            const metaInfPath = path.resolve(__dirname, 'dist/META-INF');
            if(!fs.existsSync(metaInfPath)){
              fs.mkdirSync(metaInfPath, {recursive: true});
              fs.writeFileSync(path.join(metaInfPath, 'MANIFAST.MF'), 'Manifest-Version: 1.0 \n Created-By: 1.8.0_42 (Oracle Corporation)')
            }
          })
        }
      }
    ]
  }
})
