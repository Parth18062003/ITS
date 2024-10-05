import ActivityCountsComponent from "@/components/ActivityCountsComponent";
import ActivityLogs from "@/components/ActivityLogs";
import ActivitySummaryComponent from "@/components/ActivitySummaryComponent";
import ListUsers from "@/components/ListUsers";
import UserActivitiesComponent from "@/components/UserActivitiesComponent";
import React from "react";

const ActivityLogsPage = () => {
  const userId = "0e235bc4-0f4a-47ff-b86e-08fb9ade88e4"; // Get the user ID from the URL
  return (
    <div className="translate-x-20 max-w-7xl"> 
      <ActivityCountsComponent />
      <ActivitySummaryComponent userId={userId} />
      <UserActivitiesComponent userId={userId} /> 
      <ListUsers />
    </div>
  );
};

export default ActivityLogsPage;
