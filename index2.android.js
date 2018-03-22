'use strict';

import React, {Component} from 'react';
import {AppRegistry, StyleSheet, Text, View} from 'react-native'

export default class Root extends Component {
    render() {
        return (
            <View style={styles.container}>
                <Text style={styles.welcome}>React Native组件</Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#65A35F',
    },
    welcome: {
        fontSize: 40,
        textAlign: 'center',
        margin: 10,
    }
});

AppRegistry.registerComponent('nodesignmodeframework', () => Root);

//AppRegistry.registerComponent('nodesignmodeframework', () => App);

