//
//  ContentView.swift
//  Remote Logger Test
//
//  Created by Baruch Nurilov on 26/01/2020.
//  Copyright © 2020 Baruch Nurilov. All rights reserved.
//

import SwiftUI
import RemoteLogger

struct ContentView: View {
    var body: some View {
        Text(RemoteLogger().sendMessage())
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
