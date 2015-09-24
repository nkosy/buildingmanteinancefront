package com.nkosi.buildingmanteinancefront.model;

import java.util.List;

/**
 * Created by nkosi on 2015/09/24.
 */
public class BuildingManager {
        private long id;
        private String manager_name;
        private List<Building> buildings;
        private BuildingManager() {
        }
        public BuildingManager(Builder builder) {
            this.id = builder.id;
            this.manager_name = builder.manager_name;
            this.buildings = builder.buildings;
        }
        public static class Builder {
            private long id;
            private String manager_name;
            private List<Building> buildings;
            public Builder(String name) {
                this.manager_name = name;
            }
            public Builder resID(Long value) {
                this.id = value;
                return this;
            }
            public Builder manager_name(String value) {
                this.manager_name = value;
                return this;
            }
            public Builder buildings(List<Building> value) {
                this.buildings = value;
                return this;
            }
            public Builder copy(BuildingManager value) {
                this.id = value.id;
                this.manager_name = value.manager_name;
                this.buildings = value.buildings;
                return this;
            }
            public BuildingManager build() {
                return new BuildingManager(this);
            }
        }
        public long getManager_id() {
            return id;
        }
        public String getManager_name() {
            return manager_name;
        }
        public List<Building> getBuildings() {
            return buildings;
        }
        public long getId() {
            return id;
        }
    }

