/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.model;

import com.gti.windowcleaning.data.StorageI;
import com.gti.windowcleaning.mocks.StorageMock;

/**
 *
 * @author xach
 */
abstract class Model {
    protected StorageI storage = new StorageMock();

    
}
