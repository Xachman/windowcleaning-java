/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.model;

import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.StorageI;

/**
 *
 * @author xach
 */
public class JobsModel extends Model<Job> {
    
    public JobsModel(StorageI storage) {
        super(storage, Job.class);
    }
    public JobsModel() {
        super(Job.class);
    }
    
}
