var path = require('path');
var ExtractTextPlugin = require('extract-text-webpack-plugin')

var config = {
    resolve: {root: path.resolve(__dirname, 'src')},
    entry: path.resolve(__dirname, 'src/main.jsx'),
    output: {
        path: path.resolve(__dirname, '../resources/assets'),
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/, loader: 'babel-loader',
                query: {
                    presets: ['es2015', 'react']
                }
            }, {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract('style-loader', 'css-loader')
            },
            {
                test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: 'url-loader?limit=10000&minetype=application/font-woff'
            },
            {
                test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: 'file-loader'
            }
        ]
    },
    plugins: [
        new ExtractTextPlugin('bundle.css')
    ]
};

module.exports = config;